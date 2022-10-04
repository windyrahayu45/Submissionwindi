package com.example.submissionwindi.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissionwindi.data.repository.StoryRepository
import com.example.submissionwindi.data.source.remote.request.LoginRequest
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoginViewModel(private val storyRepository: StoryRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState get() = _uiState

    fun login(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            storyRepository.getLogin(
                loginRequest = LoginRequest(
                    email = email,
                    password = password
                )
            ).onStart {
                uiState.update {
                    it.copy(
                        loading = true
                    )
                }
            }.onCompletion {
                uiState.update {
                    it.copy(
                        loading = false
                    )
                }
            }.catch {
                uiState.update { loginUiState ->
                    loginUiState.copy(
                        error = it.message ?: "error",
                        loginSuccess = false
                    )
                }
            }.collect { data ->
                uiState.update {
                    it.copy(
                        data = data.loginResult.token,
                        loginSuccess = true
                    )
                }
            }
        }
    }
}