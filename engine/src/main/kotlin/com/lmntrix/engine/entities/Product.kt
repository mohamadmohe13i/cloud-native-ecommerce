package com.lmntrix.engine.entities

import jakarta.persistence.*

@Entity
//@Table(
//    name = "country",
//    indexes = {
//        @Index(name = "my_index_name", columnList="iso_code,name", unique = true)
//    }
//)
//@Table(
//    indexes = [
////        Index(name = "prct_index", columnList = "name", unique = false)
//    ]
//)
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false)
    var name: String? = null

//    @Column(nullable = false)
//    var description: String? = null


}