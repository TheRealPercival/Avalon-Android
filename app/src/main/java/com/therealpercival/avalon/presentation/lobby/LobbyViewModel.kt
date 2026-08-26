package com.therealpercival.avalon.presentation.lobby

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class LobbyViewModel @Inject constructor() : ViewModel() {
    data class Player(
        val displayName: String,
        val avatarModel: Any
    )

    data class UiState(
        val selectedPreset: String = "",
        val presetOptions: List<String> = emptyList(),
        val isPresetDropdownExpanded: Boolean = false,
        val players: List<Player> = emptyList()
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()
}
