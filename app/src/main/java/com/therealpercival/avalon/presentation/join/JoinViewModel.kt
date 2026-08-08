package com.therealpercival.avalon.presentation.join

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class JoinViewModel @Inject constructor() : ViewModel() {
    data class UiState(
        val joinText: String = "",
        val imageModels: List<Any> = emptyList(),
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()
}
