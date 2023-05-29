package com.lmntrix.engine.configs

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException):
            ResponseEntity<Map<String, String?>> {
        val errorsList: Map<String, String?> = e.fieldErrors.associate { it.field to it.defaultMessage }
        return ResponseEntity(errorsList, HttpStatus.BAD_REQUEST)
    }


}