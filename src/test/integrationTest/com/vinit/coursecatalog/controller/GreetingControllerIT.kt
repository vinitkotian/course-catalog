package com.vinit.coursecatalog.controller

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("integrationTest")
@AutoConfigureWebTestClient
class GreetingControllerIT {

    @Autowired
    lateinit var webTestClient :  WebTestClient

    val name = "Vinit"

    @Test
    fun `should retrieve greeting message with name`(){
        val greetingResponse = webTestClient.get()
            .uri("v1/greeting/{name}",name)
            .exchange() //Makes the actual request.
            .expectStatus().is2xxSuccessful
            .expectBody(String::class.java)
            .returnResult() //Returns response result

        Assertions.assertEquals("Hello $name", greetingResponse.responseBody)
    }
}
