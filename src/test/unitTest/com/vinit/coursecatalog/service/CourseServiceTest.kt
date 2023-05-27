//package com.vinit.coursecatalog.service
//
//import com.vinit.coursecatalog.dto.CourseDTO
//import com.vinit.coursecatalog.repository.CourseRepository
//import io.kotlintest.shouldBe
//import io.mockk.mockk
//import io.mockk.mockkClass
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.extension.ExtendWith
//import org.mockito.junit.jupiter.MockitoExtension
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
//
//@Test
//private class CourseServiceTest {
////    @Autowired
////    lateinit var courseRepository: CourseRepository
//
//    fun `should return course deatils after successfully adding a course to Db`(){
//        val courseRepository  = mockk<CourseRepository>()
//        val courseService = CourseService(courseRepository)
//        val courseDTO = CourseDTO(
//            name = "Docker",
//            id = "001".toInt(),
//            category = "Infra"
//        )
//
//        courseService.addCourse(courseDTO) shouldBe courseDTO
//
//    }
//}
