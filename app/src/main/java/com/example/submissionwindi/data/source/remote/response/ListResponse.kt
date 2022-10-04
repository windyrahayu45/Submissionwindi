package com.example.submissionwindi.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class ListResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("listStory")
    val listStory: List<Story>,
    @SerializedName("message")
    val message: String
)