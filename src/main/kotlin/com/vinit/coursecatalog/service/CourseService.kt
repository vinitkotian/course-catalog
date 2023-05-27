package com.vinit.coursecatalog.service

import com.vinit.coursecatalog.dto.CourseDTO
import com.vinit.coursecatalog.entity.Course
import com.vinit.coursecatalog.repository.CourseRepository
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class CourseService(val courseRepository: CourseRepository) {

    companion object: KLogging()

    fun addCourse(courseDTO: CourseDTO): CourseDTO {
        val courseEntity = courseDTO.let{
            Course(it.id,it.name,it.category)
        }

        courseRepository.save(courseEntity)

        logger.info("Saved course ${courseEntity.id} ${courseEntity.name}")
        return courseEntity.let{
            CourseDTO(
                it.name,
                it.id,
                it.category
            )
        }
    }

}
