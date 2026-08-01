package com.therealpercival.avalon.presentation.join

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class JoinViewModel : ViewModel() {
    data class UiState(
        val joinText: String = "",
        val imageModels: List<Any> = emptyList(),
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()
}
