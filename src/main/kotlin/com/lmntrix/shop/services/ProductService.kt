package com.lmntrix.shop.services

import com.lmntrix.shop.dtos.ProductDTO
import com.lmntrix.shop.entities.Product
import com.lmntrix.shop.repositories.ProductRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class ProductService(private val repository: ProductRepository) {

    fun save(productDTO: ProductDTO): ProductDTO {
        val product = Product(name = productDTO.name!!)
        repository.save(product)
        return ProductDTO(id = product.id, name = product.name)
    }

    @Cacheable("products")
    fun find(id: Long): ProductDTO {
        /**
        for cache test
         */
        Thread.sleep(1000)
        val product = repository.findById(id)
        return ProductDTO(id = product.get().id, name = product.get().name)
    }
}