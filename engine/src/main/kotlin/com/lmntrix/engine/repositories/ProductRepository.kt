package com.lmntrix.engine.repositories

import com.lmntrix.engine.entities.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long>