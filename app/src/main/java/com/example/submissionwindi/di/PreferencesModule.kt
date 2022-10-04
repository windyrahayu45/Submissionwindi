package com.example.submissionwindi.di

import android.content.Context
import android.content.SharedPreferences
import com.example.submissionwindi.data.source.local.StoryPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val preferencesModule = module {
    single {
        initPref(androidContext())
    }

    single {
        StoryPreferences(get())
    }
}

private fun initPref(context: Context): SharedPreferences {
    return context.getSharedPreferences(StoryPreferences.PREFERENCES_NAME, Context.MODE_PRIVATE)
}