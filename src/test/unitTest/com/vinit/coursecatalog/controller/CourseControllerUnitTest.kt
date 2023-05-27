package com.vinit.coursecatalog.controller

import com.ninjasquad.springmockk.MockkBean
import com.vinit.coursecatalog.dto.CourseDTO
import com.vinit.coursecatalog.entity.Course
import com.vinit.coursecatalog.service.CourseService
import io.kotlintest.shouldBe
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest(controllers = [CourseController::class])
class CourseControllerUnitTest {

    @MockkBean
    lateinit var courseServiceMock : CourseService

    @Test
    fun `should add requested course`(){
        val courseController = CourseController(courseServiceMock)
        val expectedCourseDTO = CourseDTO(
            id= 1,
            name = "React, beginners guide",
            category = "Development"
        )
        every {
            courseServiceMock.addCourse(any())
        } returns  expectedCourseDTO

        val requestBody = CourseDTO(
            id= 1,
            name = "React, beginners guide",
            category = "Development"
        )

        courseController.addCourse(requestBody) shouldBe expectedCourseDTO
    }
}
