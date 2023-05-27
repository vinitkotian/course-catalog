package com.vinit.coursecatalog.controller

import com.vinit.coursecatalog.service.GreetingService
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/greeting")
class GreetingController(val greetingService: GreetingService) {

    @GetMapping("/{name}")
    fun retrieveGreeting(@PathVariable("name") name: String): String{
        return greetingService.retrieveGreeting(name)
    }
}
