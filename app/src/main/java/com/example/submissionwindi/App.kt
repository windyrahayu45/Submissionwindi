package com.example.submissionwindi

import android.app.Application
import com.example.submissionwindi.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                listOf(
                    preferencesModule,
                    dataSourceModule,
                    networkModule,
                    repositoryModule,
                    serviceModule,
                    viewModelModule
                )
            )
        }
    }
}