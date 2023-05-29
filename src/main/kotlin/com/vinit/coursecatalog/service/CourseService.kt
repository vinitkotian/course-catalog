package com.vinit.coursecatalog.service


import com.vinit.coursecatalog.dto.CourseDTO
import com.vinit.coursecatalog.entity.Course
import com.vinit.coursecatalog.exceptions.CourseNotFoundException
import com.vinit.coursecatalog.repository.CourseRepository
import java.util.Optional
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

    fun getAllCourses():List<CourseDTO> {
        return courseRepository.findAll().map{
            CourseDTO(
                name = it.name,
                id = it.id,
                category = it.category
            )
        }
    }

    fun updateCourse(courseDTO: CourseDTO, courseId: Int) : CourseDTO{
        val existingCourse = courseRepository.findById(courseId)

        if(existingCourse.isPresent){
            return existingCourse.get()
                .let {
                    it.name = courseDTO.name
                    it.category= courseDTO.category
                    courseRepository.save(it)
                    CourseDTO(
                        id = it.id,
                        name = it.name,
                        category = it.category
                    )
            }
        }else{
            throw CourseNotFoundException("Requested course not found for Id : $courseId")
        }
    }

}
