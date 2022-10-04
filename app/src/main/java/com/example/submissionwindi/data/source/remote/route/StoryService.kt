package com.example.submissionwindi.data.source.remote.route

import com.example.submissionwindi.data.source.remote.request.LoginRequest
import com.example.submissionwindi.data.source.remote.request.RegisterRequest
import com.example.submissionwindi.data.source.remote.response.AddStoryResponse
import com.example.submissionwindi.data.source.remote.response.ListResponse
import com.example.submissionwindi.data.source.remote.response.LoginResponse
import com.example.submissionwindi.data.source.remote.response.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface StoryService {
    @POST("register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): RegisterResponse

    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @GET("stories")
    suspend fun listStory(): ListResponse

    @Multipart
    @POST("stories")
    suspend fun addStory(
        @Part("description") description : RequestBody,
        @Part photo : MultipartBody.Part,
        @Part("lat") lat : RequestBody,
        @Part("lon") lon : RequestBody
    ): AddStoryResponse
}