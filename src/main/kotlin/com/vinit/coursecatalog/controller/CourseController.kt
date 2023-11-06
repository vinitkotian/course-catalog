package com.vinit.coursecatalog.controller

import com.vinit.coursecatalog.models.CourseDTO
import com.vinit.coursecatalog.service.CourseService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/courses/")
class CourseController(private val courseService: CourseService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addCourse(@RequestBody  @Validated courseDTO: CourseDTO): CourseDTO {
        return courseService.addCourse(courseDTO)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllCourses() : List<CourseDTO>{
        return courseService.getAllCourses()
    }
    @PutMapping("/{courseId}")
    fun updateCourse(@RequestBody courseDTO: CourseDTO, @PathVariable("courseId") courseId: Int): CourseDTO {
        return courseService.updateCourse(courseDTO,courseId)
    }

    @DeleteMapping("/{courseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCourseById(@PathVariable courseId: Int) {
        courseService.deleteCourseById(courseId)
    }

}
