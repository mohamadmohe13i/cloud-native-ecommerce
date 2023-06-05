package com.lmntrix.engine.entities


import jakarta.annotation.PostConstruct
import jakarta.persistence.*
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Service
import java.util.*


data class FiltersGroupDTO(
    var attributes: List<Attribute>
)

data class CategoryFiltersDTO(
    var filtersGroup: List<FiltersGroupDTO>
)

@Service
class Test2(var categoryRepository: CategoryRepository, var inventoryRepository: InventoryRepository) {

    @Transactional
    fun categoryFilters() {
        val categoryFiltersDTOList = categoryRepository.findByName("sun glasses")
            .get().defaultAttributes?.map { FiltersGroupDTO(it.attributes) }?.toList()
        val result = "salam"
    }

}

@Service
class TestOnProduct(
    var product2Repository: Product2Repository,
    var fieldTypeRepository: FieldTypeRepository,
    var categoryRepository: CategoryRepository,
    var attributeGroupRepository: AttributeGroupRepository,
    var test2: Test2,
) {

    @PostConstruct
    fun seed() {
        // create a category
        test2.categoryFilters()
        val x = categoryRepository.findByName("sun glasses").get().defaultAttributes
        seedFieldType()
        val textFieldType = fieldTypeRepository.findByName("text").get()

        if (categoryRepository.findByName("sun glasses").isEmpty) {
            val category = Category(name = "sun glasses", defaultAttributes = null)
            categoryRepository.save(category)
        }

        if (attributeGroupRepository.findAll().isEmpty()) {
            val category = categoryRepository.findByName("sun glasses").get()
            val attributeValue1 = AttributeValue(value = "metal")
            val attributeValue2 = AttributeValue(value = "wood")
            val attributeValue3 = AttributeValue(value = "steel")

            val attribute1 = Attribute(
                hasMoreThanOneValue = false,
                name = "material",
                isSelectBox = true,
                fieldType = textFieldType,
                values = mutableListOf(attributeValue1, attributeValue2, attributeValue3)
            )
            attribute1.values.forEach { it.attribute = attribute1 }

            val attributeGroup =
                AttributeGroup(name = "general information", attributes = listOf(attribute1), category = category)
            attributeGroup.attributes.forEach { it.attributeGroup = attributeGroup }
            attributeGroupRepository.save(attributeGroup)

            category.defaultAttributes = mutableListOf(attributeGroup)
            categoryRepository.save(category)
        }
    }

    fun seedFieldType() {
        if (fieldTypeRepository.findAll().isEmpty()) {
            val textFieldType = FieldType(name = "text")
            val colorFieldType = FieldType(name = "color")
            fieldTypeRepository.save(textFieldType)
            fieldTypeRepository.save(colorFieldType)
        }
    }


}


interface InventoryRepository : JpaRepository<Inventory, Long> {
    @Query("SELECT P, I, AG, AT, AV FROM Product2 AS P " +
            "JOIN P.inventories AS I " +
            "JOIN P.attributeGroups AS AG " +
            "JOIN AG.attributes AS AT " +
            "JOIN AT.values as AV " +
                "WHERE I.quantity > 0 " +
                "AND AT.name = :name and AV.value IN(:values)")
    fun getInventories()
}

interface CategoryRepository : JpaRepository<Category, Long> {
    fun findByName(name: String): Optional<Category>
}

interface Product2Repository : JpaRepository<Product2, Long>
interface FieldTypeRepository : JpaRepository<FieldType, Long> {
    fun findByName(name: String): Optional<FieldType>
}


@Entity
class Product2(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    var name: String,

    @ManyToOne
    var cateory: Category,

    @OneToMany(mappedBy = "product", targetEntity = AttributeGroup::class, cascade = [CascadeType.ALL])
    var attributeGroups: List<AttributeGroup>?,

    @OneToMany(mappedBy = "product", targetEntity = Inventory::class, cascade = [CascadeType.ALL])
    var inventories: List<Inventory>?

)

@Entity
class Inventory(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    var quantity: Long,

    @ManyToOne
    var product: Product2,

    @OneToMany(mappedBy = "inventory", targetEntity = Specification::class)
    var specifications: List<Specification>
)

@Entity
class Specification(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    var key: String,
    var value: String,

    @ManyToOne
    var fieldType: FieldType,

    @ManyToOne
    var inventory: Inventory,

    var orderId: Long? = 0,
)

@Entity
class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    var name: String,

//    @OneToOne(fetch = FetchType.LAZY)
//    @Column(nullable = true)
//    var parentId: Category?,

    @OneToMany(mappedBy = "category", targetEntity = AttributeGroup::class, cascade = [CascadeType.ALL])
    var defaultAttributes: List<AttributeGroup>?

)

@Entity
class AttributeGroup(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    var name: String,
    var orderId: Long = 0,

    @ManyToOne
    @JoinColumn(nullable = true)
    var category: Category? = null,

    @ManyToOne
    @JoinColumn(nullable = true)
    var product: Product2? = null,

    @OneToMany(
        mappedBy = "attributeGroup",
        targetEntity = Attribute::class, fetch = FetchType.EAGER,
        cascade = [CascadeType.ALL]
    )
    var attributes: List<Attribute>,
)

interface AttributeGroupRepository : JpaRepository<AttributeGroup, Long>

@Entity
class Attribute(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    var name: String,

    @Column(nullable = true)
    var hasMoreThanOneValue: Boolean? = false,

    @Column(nullable = true)
    var isSelectBox: Boolean? = false,

    var orderId: Long? = 0,

    @OneToMany(mappedBy = "attribute", targetEntity = AttributeValue::class, cascade = [CascadeType.ALL])
    var values: List<AttributeValue>,

    @ManyToOne
    var attributeGroup: AttributeGroup? = null,

    @ManyToOne
    var fieldType: FieldType,
)

@Entity
class AttributeValue(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    var value: String,
    var orderId: Long? = 0,

    @ManyToOne
    var attribute: Attribute? = null,
)

@Entity
class FieldType(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    var name: String,
)



