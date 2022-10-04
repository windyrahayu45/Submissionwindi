package com.example.submissionwindi.di

import com.example.submissionwindi.ui.login.LoginViewModel
import com.example.submissionwindi.ui.register.RegisterViewModel
import com.example.submissionwindi.ui.splash.SplashViewModel
import com.example.submissionwindi.ui.story.list.ListViewModel
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

    viewModel {
        ListViewModel(get())
    }
}