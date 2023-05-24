package com.lmntrix.shop.dtos

import jakarta.validation.constraints.NotBlank

data class ProductDTO(
    var id: Long? = null,
    @field:NotBlank(message = "name is required.") var name: String?
)

