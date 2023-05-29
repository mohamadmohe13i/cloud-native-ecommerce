package com.lmntrix.engine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.lmntrix.engine", "com.lmntrix.core"])
class EngineApplication

fun main(args: Array<String>) {
    runApplication<EngineApplication>(*args)
}
