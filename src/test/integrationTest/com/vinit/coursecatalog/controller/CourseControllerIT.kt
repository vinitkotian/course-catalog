package com.vinit.coursecatalog.controller

import com.vinit.coursecatalog.dto.CourseDTO
import com.vinit.coursecatalog.repository.CourseRepository
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBodyList
import testUtils.getCourseEntityList

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integrationTest")
@AutoConfigureWebTestClient
class CourseControllerIT {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var courseRepository: CourseRepository

    @BeforeEach
    fun setUp(){
        courseRepository.deleteAll()
        val courses = getCourseEntityList()
        courseRepository.saveAll(courses)
    }

    @Test
    fun `should add requested course to Db`() {
       val courseDTO = CourseDTO(
           name = "Kubernetes",
           id = null,
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

        recievedCourseDTO!!.name shouldBe courseDTO.name
        recievedCourseDTO!!.category shouldBe courseDTO.category
        recievedCourseDTO!!.id shouldNotBe null
    }

    @Test
    fun `should retrieve all courses`() {
        val recievedCourses = webTestClient
            .get()
            .uri("v1/courses/")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(CourseDTO::class.java)
            .returnResult()
            .responseBody

        recievedCourses!!.size shouldBe 3
    }
}
