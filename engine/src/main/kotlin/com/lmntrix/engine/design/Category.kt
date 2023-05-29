package com.lmntrix.engine.design

import jakarta.persistence.Id

data class Category(
    private var name: String,
    private var parentId: Long?
)

data class AttributeType(
    private var name: String
)

class AttributeGroup(
    private var name: String,
    var orderId: Long,
    private var attributes: List<AttributeGroupLineItem>
)

open class AttributeGroupLineItem(
    var orderId: Long,
)

class SimpleAttributeGroupLineItem(
    private var attribute: Attribute,
    private var value: String,
    orderId: Long,
    ) : AttributeGroupLineItem(orderId)

class MultipleAttributeGroupLineItem(
    var attributes: List<SimpleAttributeGroupLineItem>,
    orderId: Long,
) : AttributeGroupLineItem(orderId)


class Attribute(
    private var name: String,
    private var attributeType: AttributeType
)

class Product(
    private var name: String,
    private var description: String,
    private var category: Category,
    private var attributeGroups: List<AttributeGroup>
)

fun main() {
    val floatAttributeType = AttributeType(name = "float")
    val textAttributeType = AttributeType(name = "text")

    val lengthAttribute = Attribute(name = "length", attributeType = floatAttributeType)
    val weightAttribute = Attribute(name = "weight", attributeType = floatAttributeType)
    val sphAttribute = Attribute(name = "sph", attributeType = textAttributeType)
    val faceAttribute = Attribute(name = "faceType", attributeType = textAttributeType)

    val attributeGroupLineItem1 =
        SimpleAttributeGroupLineItem(attribute = lengthAttribute, value = "100", orderId = 3)
    val attributeGroupLineItem2 =
        SimpleAttributeGroupLineItem(attribute = weightAttribute, value = "10.2", orderId = 1)
    val attributeGroupLineItem3 =
        SimpleAttributeGroupLineItem(attribute = sphAttribute, value = "+0.5", orderId = 2)
    val attributeGroupLineItem4 =
        SimpleAttributeGroupLineItem(attribute = faceAttribute, value = "Circle", orderId = 3)
    val attributeGroupLineItem5 =
        SimpleAttributeGroupLineItem(attribute = faceAttribute, value = "Rectangle", orderId = 4)
    val multipleAttributeGroupLineItem =
        MultipleAttributeGroupLineItem(
            attributes = listOf(attributeGroupLineItem4, attributeGroupLineItem5).sortedBy { it.orderId },
            orderId = 4
        )
    val attributeGroup = AttributeGroup(
        name = "general information", attributes = listOf(
            attributeGroupLineItem1,
            attributeGroupLineItem2,
            attributeGroupLineItem3,
            multipleAttributeGroupLineItem
        ).sortedBy { it.orderId }, orderId = 1
    )

    val product = Product(
        name = "glass",
        "is fine.",
        category = Category(name = "glasses", parentId = null),
        attributeGroups = listOf(attributeGroup).sortedBy { it.orderId }
    )

    print(product)

}