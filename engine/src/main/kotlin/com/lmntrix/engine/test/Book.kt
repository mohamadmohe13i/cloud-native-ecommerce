package com.lmntrix.engine.test

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.gson.Gson
import com.lmntrix.engine.design.*
import com.lmntrix.engine.entities.AttributeGroupRepository
import jakarta.annotation.PostConstruct
import jakarta.persistence.*
import org.hibernate.annotations.ColumnTransformer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service


@Entity
@Table
class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false, columnDefinition = "jsonb")
    @Convert(converter = MyConverter::class)
    @ColumnTransformer(write = "?::jsonb")
    var data: Product? = null
}

@Converter(autoApply = true)
class MyConverter : AttributeConverter<Product?, String> {
    override fun convertToDatabaseColumn(mjo: Product?): String {
        return objectMapper.writeValueAsString(mjo)
    }

    override fun convertToEntityAttribute(dbData: String): Product? {
        return objectMapper.readValue(dbData, Product::class.java)
    }

    companion object {
        private val GSON = Gson()
        private val objectMapper = jacksonObjectMapper()
    }
}

interface BookRepository : JpaRepository<Book, Long> {

}

@Service
class BookService {

    @Autowired
    private lateinit var bookRepository: BookRepository
    @Autowired
    private lateinit var attributeGroupRepository: AttributeGroupRepository
    @PostConstruct
    public fun test() {
        val product = createBook()
        val book = Book()
        book.data = product
        bookRepository.save(book)
    }

}

fun createCategoryDefaults(): List<AttributeGroup> {
    val textAttributeType = AttributeType(name = "text")
    val ramAttribute = Attribute(
        name = "ram", attributeType = textAttributeType,
        hasMoreThanOneValue = false, isSelectBox = true,
        values = listOf(AttributeValue("8 G"), AttributeValue("6 GB"))
    )
    return listOf(AttributeGroup("specification", attributes = listOf(ramAttribute)))
}

fun createBook(): Product {
    val floatAttributeType = AttributeType(name = "float")
    val textAttributeType = AttributeType(name = "text")

    val lengthAttribute = Attribute(
        name = "length", attributeType = floatAttributeType, orderId = 3,
        values = listOf(AttributeValue(value = "100"))
    )
    val weightAttribute = Attribute(
        name = "weight", attributeType = floatAttributeType, orderId = 1,
        values = listOf(AttributeValue(value = "10.2"))
    )
    val sphAttribute =
        Attribute(
            name = "sph", attributeType = textAttributeType, orderId = 2,
            values = listOf(AttributeValue(value = "+0.5"))
        )
    val faceAttribute = Attribute(name = "faceType", attributeType = textAttributeType, orderId = 0,
        values = listOf(AttributeValue(value = "Circle"), AttributeValue(value = "Rectangle")).sortedBy { it.orderId }
    )

    val attributeGroup = AttributeGroup(
        name = "general information", attributes = listOf(
            lengthAttribute,
            weightAttribute,
            sphAttribute,
            faceAttribute
        ).sortedBy { it.orderId }, orderId = 1
    )

    val product = Product(
        name = "glass",
        "is fine.",
        category = Category(name = "glasses", parentId = null),
        attributeGroups = listOf(attributeGroup).sortedBy { it.orderId }
    )

    return product
}



