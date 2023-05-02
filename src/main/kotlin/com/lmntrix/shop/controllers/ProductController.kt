package com.lmntrix.shop.controllers

import com.lmntrix.shop.dtos.ProductDTO
import com.lmntrix.shop.services.ProductService
import jakarta.websocket.server.PathParam
import org.apache.juli.logging.Log
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
class ProductController(private val service: ProductService) {

    @PostMapping(path = ["/", ""])
    private fun save(@RequestBody bookDTO: ProductDTO): ProductDTO {
        return service.save(bookDTO)
    }

    @GetMapping("/{id}")
    private fun find(@PathVariable("id") id: Long): ProductDTO {
        return service.find(id)
    }

}