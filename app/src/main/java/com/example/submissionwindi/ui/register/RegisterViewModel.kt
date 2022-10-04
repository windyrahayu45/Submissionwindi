package com.example.submissionwindi.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissionwindi.data.repository.StoryRepository
import com.example.submissionwindi.data.source.remote.request.LoginRequest
import com.example.submissionwindi.data.source.remote.request.RegisterRequest
import com.example.submissionwindi.ui.login.LoginUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RegisterViewModel(private val storyRepository: StoryRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState get() = _uiState

    fun register(
        email: String,
        password: String,
        name: String
    ) {
        viewModelScope.launch {
            storyRepository.getRegister(
                registerRequest = RegisterRequest(
                    email = email,
                    password = password,
                    name = name
                )
            ).onStart {
                _uiState.update {
                    it.copy(
                        loading = true
                    )
                }
            }.onCompletion {
                _uiState.update {
                    it.copy(
                        loading = false
                    )
                }
            }.catch {
                _uiState.update { registerUiState ->
                    registerUiState.copy(
                        error = it.message ?: "error",
                        registerSuccess = false
                    )
                }
            }.collect { data ->
                _uiState.update {
                    it.copy(
                        data = data.message,
                        registerSuccess = true
                    )
                }
            }
        }
    }
}