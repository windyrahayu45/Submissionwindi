package com.example.submissionwindi.ui.login

data class LoginUiState (
    val data: String = "",
    val error: String = "",
    val loading: Boolean = false,
    val loginSuccess: Boolean = false
)