package com.example.di.module

import com.example.data.api.CourseApi
import com.example.data.repository.CourseRepositoryImpl
import com.example.domain.viewmodels.repo.ICourseRepository
import com.example.presentation.fragment.main.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(CourseApi::class.java) }

    single<ICourseRepository> { CourseRepositoryImpl(get()) }

    viewModel { MainViewModel(get()) }
}
