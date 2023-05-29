package com.lmntrix.core.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController("coreProductController")
@RequestMapping("/product2")
class ProductController {


    @GetMapping("/{id}")
    private fun find(@PathVariable("id") id: Long): String {
        return "salam"
    }

}