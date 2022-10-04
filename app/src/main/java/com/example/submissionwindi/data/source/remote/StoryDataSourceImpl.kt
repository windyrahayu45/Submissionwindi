package com.example.submissionwindi.data.source.remote

import com.example.submissionwindi.data.source.remote.request.LoginRequest
import com.example.submissionwindi.data.source.remote.request.RegisterRequest
import com.example.submissionwindi.data.source.remote.response.LoginResponse
import com.example.submissionwindi.data.source.remote.response.RegisterResponse
import com.example.submissionwindi.data.source.remote.response.Story
import com.example.submissionwindi.data.source.remote.route.StoryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class StoryDataSourceImpl (private val storyService : StoryService) : StoryDataSource{
    override fun getRegister(registerRequest: RegisterRequest): Flow<RegisterResponse> {
        return flow{
            emit(
                storyService.register(
                    registerRequest = registerRequest
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    override fun getLogin(loginRequest: LoginRequest): Flow<LoginResponse> {
        return flow{
            emit(
                storyService.login(
                    loginRequest = loginRequest
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    override fun getStory(): Flow<List<Story>> {
        return flow {
            emit(
                storyService.listStory().listStory
            )
        }.flowOn(Dispatchers.IO)
    }
}