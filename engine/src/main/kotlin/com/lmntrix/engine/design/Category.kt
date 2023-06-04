package com.lmntrix.engine.design

import jakarta.persistence.Id

data class Category(
    var name: String? = null,
    var parentId: Long? = null,
    var defaults: List<AttributeGroup>? = null
)

data class AttributeType(
    var name: String? = null
)

class AttributeGroup(
    var name: String? = null,
    var orderId: Long? = null,
    var attributes: List<Attribute>? = null
)

//open class AttributeGroupLineItem(
//    var orderId: Long? = null,
//)

class Attribute(
    var name: String? = null,
    var attributeType: AttributeType? = null,
    var hasMoreThanOneValue: Boolean? = false,
    var isSelectBox: Boolean? = false,
    var values: List<AttributeValue>? = null,
    var orderId: Long? = null,
)

//class MultipleAttributeGroupLineItem(
//    var attributes: List<SimpleAttributeGroupLineItem>? = null,
//    orderId: Long,
//) : AttributeGroupLineItem(orderId)


class AttributeValue(
     var value: String? = null,
     var orderId: Long? = 0,
)

class Product(
     var name: String? = null,
     var description: String? = null,
     var category: Category? = null,
     var attributeGroups: List<AttributeGroup>? = null
)


class Category2() {
    private lateinit var name: String
    private var parentId: Long? = null
    // defaults
}
// category 2 => digital devices
/**
name: "mobile",
parentId: "2"
defaultAttributes: [
[
{
"name" : "ram"
"type" : "text"
"isSelectBox" : true
"hasMoreThanOneValue": false
"values" : [{value: "8GB", orderId: 1}, {value: "6GB", orderId:2}]
},
{
"name": "cpu"
"type": "text"
"isSelectBox": false
"hasMoreThanOneValue": false
"values" : null
}
]
]
 */

//fun main() {
//    val floatAttributeType = AttributeType(name = "float")
//    val textAttributeType = AttributeType(name = "text")
//
//    val lengthAttribute = Attribute(name = "length", attributeType = floatAttributeType)
//    val weightAttribute = Attribute(name = "weight", attributeType = floatAttributeType)
//    val sphAttribute = Attribute(name = "sph", attributeType = textAttributeType)
//    val faceAttribute = Attribute(name = "faceType", attributeType = textAttributeType)
//
//    val attributeGroupLineItem1 =
//        SimpleAttributeGroupLineItem(attribute = lengthAttribute, value = "100", orderId = 3)
//    val attributeGroupLineItem2 =
//        SimpleAttributeGroupLineItem(attribute = weightAttribute, value = "10.2", orderId = 1)
//    val attributeGroupLineItem3 =
//        SimpleAttributeGroupLineItem(attribute = sphAttribute, value = "+0.5", orderId = 2)
//    val attributeGroupLineItem4 =
//        SimpleAttributeGroupLineItem(attribute = faceAttribute, value = "Circle", orderId = 3)
//    val attributeGroupLineItem5 =
//        SimpleAttributeGroupLineItem(attribute = faceAttribute, value = "Rectangle", orderId = 4)
//    val multipleAttributeGroupLineItem =
//        MultipleAttributeGroupLineItem(
//            attributes = listOf(attributeGroupLineItem4, attributeGroupLineItem5).sortedBy { it.orderId },
//            orderId = 4
//        )
//    val attributeGroup = AttributeGroup(
//        name = "general information", attributes = listOf(
//            attributeGroupLineItem1,
//            attributeGroupLineItem2,
//            attributeGroupLineItem3,
//            multipleAttributeGroupLineItem
//        ).sortedBy { it.orderId }, orderId = 1
//    )
//
//    val product = Product(
//        name = "glass",
//        "is fine.",
//        category = Category(name = "glasses", parentId = null),
//        attributeGroups = listOf(attributeGroup).sortedBy { it.orderId }
//    )
//
//    print(product)
//
//}