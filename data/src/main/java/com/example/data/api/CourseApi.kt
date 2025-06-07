package com.example.data.api

import com.example.domain.viewmodels.entity.Course
import retrofit2.http.GET

interface CourseApi {
    @GET("courses") //localhost:8080/courses
    suspend fun getCourses(): List<Course>
}