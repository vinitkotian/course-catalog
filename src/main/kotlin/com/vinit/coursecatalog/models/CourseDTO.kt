package com.vinit.coursecatalog.models

import jakarta.validation.constraints.NotBlank

data class CourseDTO(
    @get:NotBlank(message = "Course name cannot be blank")
    val name: String,
    val id: Int?,
    @get:NotBlank(message = "Course category cannot be blank")
    val category: String
)
