package com.example.submissionwindi.data.source.remote

import com.example.submissionwindi.data.source.remote.request.LoginRequest
import com.example.submissionwindi.data.source.remote.request.RegisterRequest
import com.example.submissionwindi.data.source.remote.response.LoginResponse
import com.example.submissionwindi.data.source.remote.response.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface StoryDataSource {

    fun getRegister(registerRequest: RegisterRequest) : Flow<RegisterResponse>

    fun getLogin (loginRequest: LoginRequest ) : Flow<LoginResponse>
}