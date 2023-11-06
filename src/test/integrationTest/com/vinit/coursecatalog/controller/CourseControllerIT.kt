package com.vinit.coursecatalog.controller

import com.vinit.coursecatalog.entity.Course
import com.vinit.coursecatalog.models.CourseDTO
import com.vinit.coursecatalog.repository.CourseRepository
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import org.springframework.test.web.reactive.server.returnResult
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
    fun setUp() {
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
            .expectStatus().is2xxSuccessful
            .expectBody(Course::class.java)
            .returnResult()
            .responseBody

        recievedCourseDTO!!.name shouldBe courseDTO.name
        recievedCourseDTO.category shouldBe courseDTO.category
        recievedCourseDTO.id shouldNotBe null
    }

    @Test
    fun `should retrieve all courses`() {
        val recievedCourses = webTestClient
            .get()
            .uri("v1/courses/")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(Course::class.java)
            .returnResult()
            .responseBody

        recievedCourses!!.size shouldBe 3
    }

    @Test
    fun `should update requested course`() {
        val existingCourseEntity = courseRepository.save(
            Course(id = null, name = "course_name_1", category = "category_1")
        )

        val updateCourseRequestDTO = CourseDTO(
            id = null, name = "course_name_10", category = "category_10"
        )

        val updatedCourse = webTestClient
            .put()
            .uri("v1/courses/{courseId}", existingCourseEntity.id)
            .bodyValue(updateCourseRequestDTO)
            .exchange()
            .expectStatus().isOk
            .expectBody(Course::class.java)
            .returnResult()
            .responseBody

        updatedCourse!!.name shouldBe updateCourseRequestDTO.name
        updatedCourse.category shouldBe updateCourseRequestDTO.category
        updatedCourse.id shouldBe existingCourseEntity.id
    }

    @Test
    fun `should delete course by requested course id`() {
        val existingCourseEntity = courseRepository.save(
            Course(id = null, name = "course_name_1", category = "category_1")
        )

        webTestClient
            .delete()
            .uri("v1/courses/{courseId}", existingCourseEntity.id)
            .exchange()
            .expectStatus().isNoContent
    }

    @Test
    fun `should return 400 for incomplete add course request`() {
        val courseDTO = CourseDTO(
            name = "",
            id = null,
            category = ""
        )

        val response = webTestClient
            .post()
            .uri("v1/courses/")
            .bodyValue(courseDTO)
            .exchange()
            .expectStatus()
            .isBadRequest
            .expectBody(String::class.java)
            .returnResult()
            .responseBody

        response shouldBe "Course category cannot be blank,Course name cannot be blank"
    }
}
