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
        object DenyProfile : DialogType()
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

    fun dismissDialog() {
        _uiState.value = _uiState.value.copy(
            dialogType = null,
            selectedAllowedProfile = null,
            selectedRequestingProfile = null
        )
    }

    fun showChangeServerDialog() {
        _uiState.value = _uiState.value.copy(
            dialogType = DialogType.Server
        )
    }

    fun showSignOutDialog() {
        _uiState.value = _uiState.value.copy(
            dialogType = DialogType.SignOut
        )
    }

    fun showAssignNicknameDialog(profile: RequestingProfile) {
        _uiState.value = _uiState.value.copy(
            dialogType = DialogType.AssignNickname,
            selectedRequestingProfile = profile
        )
    }

    fun allowProfile(profile: RequestingProfile) {
        // TODO: Implement allow profile logic
    }

    fun showDenyProfileDialog(profile: RequestingProfile) {
        _uiState.value = _uiState.value.copy(
            dialogType = DialogType.DenyProfile,
            selectedRequestingProfile = profile
        )
    }

    fun denyProfile(profile: RequestingProfile) {
        // TODO: Implement deny profile logic
    }

    fun showRemoveProfileDialog(profile: AllowedProfile) {
        _uiState.value = _uiState.value.copy(
            dialogType = DialogType.RemoveProfile,
            selectedAllowedProfile = profile
        )
    }

    fun removeProfile(profile: AllowedProfile) {
        // TODO: Implement remove profile logic
    }
}
