package com.vinit.coursecatalog.controller

import com.vinit.coursecatalog.dto.CourseDTO
import com.vinit.coursecatalog.service.CourseService
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class CourseControllerTest {

    @Test
    fun `should add requested course`(){
        val courseServiceMock = mockk<CourseService>()
        val courseController = CourseController(courseServiceMock)
        val expectedCourseDTO = CourseDTO(
            id= null,
            name = "React, beginners guide",
            category = "Development"
        )
        every {
            courseServiceMock.addCourse(any())
        } returns  expectedCourseDTO

        val requestBody = CourseDTO(
            id= null,
            name = "React, beginners guide",
            category = "Development"
        )

        courseController.addCourse(requestBody) shouldBe expectedCourseDTO
    }

    @Test
    fun `should get all courses from Db`() {
        val courseServiceMock = mockk<CourseService>()
        val courseController = CourseController(courseServiceMock)
        val courseList = listOf(CourseDTO(
            name = "Next JS",
            id = null,
            category = "SSR frontend"
        ))

        every{
            courseServiceMock.getAllCourses()
        } returns courseList

        courseController.getAllCourses() shouldBe courseList
    }

    @Test
    fun `should return updated course in Db`() {
        val courseServiceMock = mockk<CourseService>()
        val courseController  = CourseController(courseServiceMock)
        val courseId = "001".toInt()
        val courseDTO = CourseDTO(
            name = "Kubernetes",
            id = null,
            category = "Infra"
        )

        every{
            courseServiceMock.updateCourse(any(),any())
        } returns courseDTO

        courseController.updateCourse(courseDTO,courseId) shouldBe courseDTO
    }
}
