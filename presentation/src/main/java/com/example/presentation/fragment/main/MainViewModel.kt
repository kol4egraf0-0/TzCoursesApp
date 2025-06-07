package com.example.presentation.fragment.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.viewmodels.repo.CourseRepository
import com.example.domain.viewmodels.entity.Course
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val repository : CourseRepository) : ViewModel()
{
    private val _courses = MutableStateFlow<List<Course>>(emptyList())
    val courses = _courses.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getCourses()
                .catch { e -> e.printStackTrace() }
                .collect { list ->
                    _courses.value = list
                }
        }
    }
}