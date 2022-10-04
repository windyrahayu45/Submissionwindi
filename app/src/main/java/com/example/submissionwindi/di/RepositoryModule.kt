package com.example.submissionwindi.di

import com.example.submissionwindi.data.repository.StoryRepository
import com.example.submissionwindi.data.repository.StoryRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<StoryRepository> {
        StoryRepositoryImpl(get(),get())
    }
}