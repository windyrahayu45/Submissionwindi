package com.example.submissionwindi.di

import com.example.submissionwindi.ui.login.LoginViewModel
import com.example.submissionwindi.ui.register.RegisterViewModel
import com.example.submissionwindi.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        LoginViewModel(get())
    }
    viewModel {
        RegisterViewModel(get())
    }

    viewModel {
        SplashViewModel(get())
    }
}