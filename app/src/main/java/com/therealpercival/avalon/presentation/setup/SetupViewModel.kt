package com.therealpercival.avalon.presentation.setup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class SetupViewModel : ViewModel() {
    sealed class ServerUrlState {
        object Unvalidated : ServerUrlState()
        object Fetching : ServerUrlState()
        object Valid : ServerUrlState()
    }
    data class UiState(
        val serverUrl: String = "",
        val serverUrlState: ServerUrlState = ServerUrlState.Unvalidated
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun setServerUrl(url: String) {
        _uiState.update { it.copy(serverUrl = url) }
    }

    fun connectToServer() {
        viewModelScope.launch {
            _uiState.update { it.copy(serverUrlState = ServerUrlState.Fetching) }
            delay(1.seconds)
            _uiState.update { it.copy(serverUrlState = ServerUrlState.Valid) }
        }
    }
}
