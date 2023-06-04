package com.lmntrix.engine.entities


import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository


@Entity
class Product2(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    var name: String,

    @ManyToOne
    var cateory: Category,
)

@Entity
class Inventory(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

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
    var id: Long,

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
    var id: Long,

    var name: String,

//    @OneToOne(fetch = FetchType.LAZY)
//    @Column(nullable = true)
//    var parentId: Category?,

    @OneToMany(mappedBy = "category", targetEntity = AttributeGroup::class)
    var defaultAttributes: List<AttributeGroup>

)

@Entity
class AttributeGroup(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    var name: String,
    var orderId: Long = 0,

    @ManyToOne
    @JoinColumn(nullable = true)
    var category: Category,

    @OneToMany(mappedBy = "attributeGroup", targetEntity = Attribute::class, fetch = FetchType.EAGER)
    var attributes: List<Attribute>,
)

interface AttributeGroupRepository : JpaRepository<AttributeGroup, Long>

@Entity
class Attribute(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    var name: String,

    @Column(nullable = true)
    var hasMoreThanOneValue: Boolean? = false,

    @Column(nullable = true)
    var isSelectBox: Boolean? = false,

    var orderId: Long? = 0,

    @OneToMany(mappedBy = "attribute", targetEntity = AttributeValue::class)
    var values: List<AttributeValue>,

    @ManyToOne
    var attributeGroup: AttributeGroup,

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
    var attribute: Attribute,
)

@Entity
class FieldType(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    var name: String,
)



