package com.lmntrix.engine.services

import com.lmntrix.engine.dtos.ProductDTO
import com.lmntrix.engine.entities.Product
import com.lmntrix.engine.mappers.ProductMapper
import com.lmntrix.engine.repositories.ProductRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val repository: ProductRepository,
    private val mapper: ProductMapper
) {

    fun save(productDTO: ProductDTO): ProductDTO {
        val product = mapper.dtoToEntity(productDTO)
        repository.save(product)
        return mapper.entityToDTO(product)
    }

    @Cacheable("products")
    fun find(id: Long): ProductDTO {
        /**
        for cache test
         */
        Thread.sleep(1000)
        val product = repository.findById(id)
        return mapper.entityToDTO(product.get())
    }
}