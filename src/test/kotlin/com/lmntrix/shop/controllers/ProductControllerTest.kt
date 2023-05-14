package com.lmntrix.shop.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.lmntrix.shop.dtos.ProductDTO
import com.lmntrix.shop.services.ProductService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@WebMvcTest
class ProductControllerTest(
    @Autowired private val mockMvc: MockMvc,
    @Autowired private val objectMapper: ObjectMapper,
) {
    private lateinit var productDTO: ProductDTO

    @MockkBean
    private lateinit var productService: ProductService

    @BeforeEach
    fun before() {
        productDTO = ProductDTO(name = "product1")
        every { productService.save(any()) } answers { firstArg() }
    }

    @Test
    fun `create product work correctly`() {
        mockMvc.perform(
            post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ProductDTO(name = "product1")))
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
    }

}