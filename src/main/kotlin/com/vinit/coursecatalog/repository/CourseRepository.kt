package com.vinit.coursecatalog.repository

import com.vinit.coursecatalog.entity.Course
import org.springframework.data.repository.CrudRepository

interface CourseRepository:CrudRepository<Course,Int>
