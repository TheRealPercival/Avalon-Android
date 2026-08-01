package com.therealpercival.avalon.presentation.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsViewModel : ViewModel() {
    data class RequestingProfile(
        val accountName: String,
        val avatarModel: Any
    )

    data class AllowedProfile(
        val displayName: String,
        val accountName: String,
        val avatarModel: Any
    )

    data class UiState(
        val serverUrl: String = "",
        val displayName: String = "",
        val accountName: String = "",
        val isAdmin: Boolean = false,
        val requestingProfiles: List<RequestingProfile> = emptyList(),
        val allowedProfiles: List<AllowedProfile> = emptyList()
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()
}
