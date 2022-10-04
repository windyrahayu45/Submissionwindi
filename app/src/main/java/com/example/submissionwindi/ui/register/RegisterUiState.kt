package com.example.submissionwindi.ui.register

data class RegisterUiState (
    val data: String = "",
    val error: String = "",
    val loading: Boolean = false,
    val registerSuccess: Boolean = false
)