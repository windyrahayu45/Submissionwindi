package com.example.submissionwindi.di

import com.example.submissionwindi.data.source.remote.route.StoryService
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {
    single {
        provideDirectoryService(get())
    }
}

private fun provideDirectoryService(retrofit: Retrofit): StoryService {
    return retrofit.create(StoryService::class.java)
}