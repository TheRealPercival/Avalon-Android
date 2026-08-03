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

    sealed class DialogType {
        object Server : DialogType()
        object SignOut : DialogType()
        object AssignNickname : DialogType()
        object RejectProfile : DialogType()
        object RemoveProfile : DialogType()
    }

    data class UiState(
        val serverUrl: String = "",
        val displayName: String = "",
        val accountName: String = "",
        val dialogType: DialogType? = null,
        val isAdmin: Boolean = false,
        val requestingProfiles: List<RequestingProfile> = emptyList(),
        val allowedProfiles: List<AllowedProfile> = emptyList(),
        val selectedAllowedProfile: AllowedProfile? = null,
        val selectedRequestingProfile: RequestingProfile? = null
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun setIsShowingDialog(dialogType: DialogType?) {
        _uiState.value = _uiState.value.copy(
            dialogType = dialogType
        )
    }

    fun allowProfile(profile: RequestingProfile) {
        // TODO: Implement allow profile logic
    }

    fun denyProfile(profile: RequestingProfile) {
        // TODO: Implement deny profile logic
    }

    fun removeProfile(profile: AllowedProfile) {
        // TODO: Implement remove profile logic
    }
}
