package com.lmntrix.shop.controllers

import com.lmntrix.shop.dtos.ProductDTO
import com.lmntrix.shop.services.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product")
class ProductController(private val service: ProductService) {

    @PostMapping(path = ["/", ""], produces = [MediaType.APPLICATION_ATOM_XML_VALUE])
    private fun save(@Validated @RequestBody bookDTO: ProductDTO): ResponseEntity<ProductDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(bookDTO))
    }

    @GetMapping("/{id}")
    private fun find(@PathVariable("id") id: Long): ProductDTO {
        return service.find(id)
    }

}