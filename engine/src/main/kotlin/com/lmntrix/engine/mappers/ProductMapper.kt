package com.lmntrix.engine.mappers

import com.lmntrix.engine.configs.MapperBeanConfig
import com.lmntrix.engine.dtos.ProductDTO
import com.lmntrix.engine.entities.Product
import org.mapstruct.Mapper

@Mapper(config = MapperBeanConfig::class)
interface ProductMapper {

    fun entityToDTO(product: Product): ProductDTO
    fun dtoToEntity(dto: ProductDTO): Product

}