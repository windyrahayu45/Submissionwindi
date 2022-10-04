package com.example.submissionwindi.data.repository

import com.example.submissionwindi.data.source.local.StoryPreferences
import com.example.submissionwindi.data.source.remote.StoryDataSource
import com.example.submissionwindi.data.source.remote.request.LoginRequest
import com.example.submissionwindi.data.source.remote.request.RegisterRequest
import com.example.submissionwindi.data.source.remote.response.LoginResponse
import com.example.submissionwindi.data.source.remote.response.RegisterResponse
import com.example.submissionwindi.data.source.remote.response.Story
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoryRepositoryImpl(
    private val storyDataSource: StoryDataSource,
    private val storyPreferences: StoryPreferences
) : StoryRepository {
    override fun getRegister(registerRequest: RegisterRequest): Flow<RegisterResponse> {
        return storyDataSource.getRegister(registerRequest = registerRequest)
    }

    override fun getLogin(loginRequest: LoginRequest): Flow<LoginResponse> {
        return storyDataSource.getLogin(loginRequest = loginRequest)
            .map {
                storyPreferences.saveToken(
                    it.loginResult.token,
                    true
                )
                return@map it
            }
    }

    override fun getStory(): Flow<List<Story>> {
        return storyDataSource.getStory()
    }
}