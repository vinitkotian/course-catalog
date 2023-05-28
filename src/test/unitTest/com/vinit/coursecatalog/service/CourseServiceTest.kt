package com.vinit.coursecatalog.service
//
//import com.ninjasquad.springmockk.MockkBean
//import com.vinit.coursecatalog.dto.CourseDTO
//import com.vinit.coursecatalog.entity.Course
//import com.vinit.coursecatalog.repository.CourseRepository
//import io.kotlintest.shouldBe
//import io.mockk.MockKAnnotations
//import io.mockk.coJustRun
//import io.mockk.justRun
//import io.mockk.mockk
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.runBlocking
//import kotlinx.coroutines.test.runBlockingTest
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//
//
//@ExperimentalCoroutinesApi
//private class CourseServiceTest {
//    @Test
//    fun `should return course deatils after successfully adding a course to Db`() = runBlockingTest{
//        val courseRepository = mockk<CourseRepository>()
//        val courseService = CourseService(courseRepository)
//        val courseDTO = CourseDTO(
//            name = "Docker",
//            id = null,
//            category = "Infra"
//        )
//        val courseEntity = courseDTO.let{
//            Course(
//                it.id,
//                it.name,
//                it.category
//            )
//        }
//
//        coJustRun { courseRepository.save(any()) }
//
//        courseService.addCourse(courseDTO) shouldBe courseDTO
//
//    }
//}
