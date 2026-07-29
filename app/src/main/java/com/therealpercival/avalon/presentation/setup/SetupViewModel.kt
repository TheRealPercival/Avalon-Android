package com.therealpercival.avalon.presentation.setup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SetupViewModel : ViewModel() {
    data class UiState(
        val serverUrl: String = "",
        val isServerUrlValid: Boolean = false
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun setServerUrl(url: String) {
        _uiState.update { it.copy(serverUrl = url) }
    }

    fun connectToServer() {
        _uiState.update { it.copy(isServerUrlValid = true) }
    }
}
