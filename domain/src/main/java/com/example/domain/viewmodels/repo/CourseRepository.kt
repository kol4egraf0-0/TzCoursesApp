package com.example.domain.viewmodels.repo

import com.example.data.api.CourseApi
import com.example.domain.viewmodels.entity.Course
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class CourseRepository(private val api: CourseApi) {
    fun getCourses(): Flow<List<Course>> = flow {
        emit(api.getCourses())
    }.flowOn(Dispatchers.IO)
}