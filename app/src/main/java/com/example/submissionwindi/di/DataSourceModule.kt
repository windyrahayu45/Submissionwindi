package com.example.submissionwindi.di

import com.example.submissionwindi.data.source.remote.StoryDataSource
import com.example.submissionwindi.data.source.remote.StoryDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<StoryDataSource> {
        StoryDataSourceImpl(get())
    }
}