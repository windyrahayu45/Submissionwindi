package com.example.submissionwindi.ui.story.list

import com.example.submissionwindi.data.source.remote.response.Story

data class ListUiState(
    val loading: Boolean = false,
    val list: List<Story> = emptyList(),
    val throwable: Throwable? = null
)
