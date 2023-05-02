package com.lmntrix.shop.repositories

import com.lmntrix.shop.entities.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long>