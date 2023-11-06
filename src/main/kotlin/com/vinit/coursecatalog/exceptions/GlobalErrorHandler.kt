package com.vinit.coursecatalog.exceptions

import mu.KLogging
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@Component
@ControllerAdvice
class GlobalErrorHandler : ResponseEntityExceptionHandler() {
    companion object: KLogging()

    override fun handleMethodArgumentNotValid(
        exception: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {

        logger.error("MethodArgumentNotValidException occurred: ${exception.message}",exception)
        val errors = exception.bindingResult.allErrors
            .map { error -> error.defaultMessage!! }
            .sorted()
        logger.info("Errors occurred: ${errors}")

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.joinToString(","){it})
    }

    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(
        exception:Exception,
        request: WebRequest
    ):ResponseEntity<Any> {
        logger.error("Exception occurred: ${exception.message}",exception)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.message)
    }
}
