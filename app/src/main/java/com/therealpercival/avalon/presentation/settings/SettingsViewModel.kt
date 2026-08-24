package com.therealpercival.avalon.presentation.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

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
        val selectedRequestingProfile: RequestingProfile? = null,
        val nickname: String = ""
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun dismissDialog() {
        _uiState.value = _uiState.value.copy(
            dialogType = null,
            selectedAllowedProfile = null,
            selectedRequestingProfile = null,
            nickname = ""
        )
    }

    fun showChangeServerDialog() {
        _uiState.value = _uiState.value.copy(
            dialogType = DialogType.Server
        )
    }

    fun changeServer() {
        // TODO: Implement change server logic
        _uiState.update { it.copy(dialogType = null) }
    }

    fun showSignOutDialog() {
        _uiState.value = _uiState.value.copy(
            dialogType = DialogType.SignOut
        )
    }

    fun signOut() {
        // TODO: Implement sign out logic
        _uiState.update { it.copy(dialogType = null) }
    }

    fun showAssignNicknameDialog(profile: RequestingProfile) {
        _uiState.value = _uiState.value.copy(
            dialogType = DialogType.AssignNickname,
            selectedRequestingProfile = profile
        )
    }

    fun setNickname(nickname: String) {
        _uiState.update { it.copy(nickname = nickname) }
    }

    fun allowProfile() {
        // TODO: Implement allow profile logic
        _uiState.update { it.copy(dialogType = null) }
    }

    fun showDenyProfileDialog(profile: RequestingProfile) {
        _uiState.value = _uiState.value.copy(
            dialogType = DialogType.DenyProfile,
            selectedRequestingProfile = profile
        )
    }

    fun denyProfile() {
        // TODO: Implement deny profile logic
        _uiState.update { it.copy(dialogType = null) }
    }

    fun showRemoveProfileDialog(profile: AllowedProfile) {
        _uiState.value = _uiState.value.copy(
            dialogType = DialogType.RemoveProfile,
            selectedAllowedProfile = profile
        )
    }

    fun removeProfile() {
        // TODO: Implement remove profile logic
        _uiState.update { it.copy(dialogType = null) }
    }
}
