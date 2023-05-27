package com.vinit.coursecatalog.controller


import com.ninjasquad.springmockk.MockkBean
import com.vinit.coursecatalog.service.GreetingService
import io.kotlintest.shouldBe
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient


@WebMvcTest(controllers = [GreetingController::class])
class GreetingControllerUnitTest {
    @MockkBean
    lateinit var greetingServiceMock: GreetingService

    @Test
    fun retrieveGreeting() {
        val name = "Vinit"

        val greetingController = GreetingController(greetingServiceMock)

        every {
            greetingServiceMock.retrieveGreeting(any())
        } returns "Hello $name"

        val greetingResponse = greetingController.retrieveGreeting(name);

        greetingResponse shouldBe "Hello $name"
    }
}
