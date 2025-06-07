package com.example.data.repository

import com.example.data.api.CourseApi
import com.example.domain.viewmodels.entity.Course
import com.example.domain.viewmodels.repo.ICourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CourseRepositoryImpl(private val api: CourseApi) : ICourseRepository {
    override fun getCourses(): Flow<List<Course>> = flow {
        emit(api.getCourses())
    }
}
