package com.example.submissionwindi.ui.story.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissionwindi.data.repository.StoryRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class ListViewModel(
    private val storyRepository: StoryRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(ListUiState())
    val uiState get() = _uiState.asStateFlow()

    fun getStory() {
        viewModelScope.launch {
            storyRepository.getStory()
                .onStart {
                    _uiState.update {
                        it.copy(
                            loading = true
                        )
                    }
                }
                .onCompletion {
                    _uiState.update {
                        it.copy(
                            loading = false
                        )
                    }
                }
                .catch {
                    _uiState.update { state ->
                        state.copy(
                            throwable = it
                        )
                    }
                }
                .collect {
                    _uiState.update { state ->
                        state.copy(
                            list = it
                        )
                    }
                }
        }
    }
}