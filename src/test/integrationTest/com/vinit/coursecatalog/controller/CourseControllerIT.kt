package com.vinit.coursecatalog.controller

import com.vinit.coursecatalog.dto.CourseDTO
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integrationTest")
@AutoConfigureWebTestClient
class CourseControllerIT {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun `should add requested course to Db`() {
       val courseDTO = CourseDTO(
           name = "Kubernetes",
           id = "002".toInt(),
           category = "Infra"
       )

       val recievedCourseDTO = webTestClient
            .post()
            .uri("v1/courses/")
            .bodyValue(courseDTO)
            .exchange()
            .expectBody(CourseDTO::class.java)
            .returnResult()
            .responseBody

        recievedCourseDTO shouldBe courseDTO
    }
}
