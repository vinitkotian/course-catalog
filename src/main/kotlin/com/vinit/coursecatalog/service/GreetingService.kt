package com.vinit.coursecatalog.service

import mu.KLogging
import org.springframework.stereotype.Service

@Service
class GreetingService {

//    @Value("\$message")
//    lateinit var message :String

    companion object : KLogging()

    fun retrieveGreeting(name: String): String{
        logger.info("Returning greeting message")
        return "Hello $name"
    }
}
