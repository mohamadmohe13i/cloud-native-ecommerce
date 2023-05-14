package com.lmntrix.shop.services

import com.lmntrix.shop.dtos.ProductDTO
import com.lmntrix.shop.entities.Product
import com.lmntrix.shop.repositories.ProductRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(SpringExtension::class)
@SpringBootTest
class ProductServiceTest {
    @Autowired
    lateinit var productService: ProductService
    private lateinit var productDTO: ProductDTO

    @MockkBean
    private lateinit var productRepository: ProductRepository

    @BeforeEach
    fun before() {
        productDTO = ProductDTO(name = "product1")
        every { productRepository.findById(any()) } answers { Optional.of(Product(name = "product1")) }
        every { productRepository.save(any()) } answers {
            val product: Product = firstArg()
            product.id = 1234L
            product
        }
        productService = ProductService(productRepository)
    }

    @Test
    fun `save works correctly`() {
        productService.save(productDTO)
        verify(exactly = 1) { productRepository.save(any()) }
    }

    @Test
    fun `entity should have id`() {
        val product = productService.save(productDTO)
        assertEquals(1234L, product.id)
    }


    @Test
    @DirtiesContext
    fun x() {
        productService.find(1)
        productService.find(1)
        verify(exactly = 1) { productRepository.findById(1) }
    }

}