package com.example.domain.viewmodels.repo

import com.example.domain.viewmodels.entity.Course
import kotlinx.coroutines.flow.Flow

interface ICourseRepository {
    fun getCourses(): Flow<List<Course>>
}