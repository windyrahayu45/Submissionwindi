package com.example.submissionwindi.ui.splash

import androidx.lifecycle.ViewModel
import com.example.submissionwindi.data.source.local.StoryPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class SplashViewModel(storyPreferences: StoryPreferences) : ViewModel() {
    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState get() = _uiState

    init {
        _uiState.update {
            it.copy(
                isLogin = storyPreferences.getToken().isNotEmpty()
            )
        }
    }
}