package com.lmntrix.shop.entities

import jakarta.persistence.*

@Entity
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?=null,
    @Column(nullable = false)
    val name: String
)