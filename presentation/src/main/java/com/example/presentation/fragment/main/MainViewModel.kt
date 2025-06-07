package com.example.presentation.fragment.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.viewmodels.entity.Course
import com.example.domain.viewmodels.repo.ICourseRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val repository : ICourseRepository) : ViewModel()
{
    private val _courses = MutableStateFlow<List<Course>>(emptyList())
    val courses: StateFlow<List<Course>> = _courses.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getCourses()
                .catch { it.printStackTrace() }
                .collect { _courses.value = it }
        }
    }
}