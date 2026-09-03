package com.therealpercival.avalon.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.therealpercival.avalon.domain.model.ConnectionStatus
import com.therealpercival.avalon.domain.repository.AdminRepository
import com.therealpercival.avalon.domain.repository.ServerRepository
import com.therealpercival.avalon.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val serverRepository: ServerRepository,
    private val adminRepository: AdminRepository
) : ViewModel() {
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
        val nickname: String = "",
        val connectionStatus: ConnectionStatus = ConnectionStatus.DISCONNECTED
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        combine(
            userRepository.getCurrentUser(),
            serverRepository.getServerUrl(),
            serverRepository.getConnectionStatus(),
            adminRepository.getRequestingProfiles(),
            adminRepository.getAllowedProfiles()
        ) { user, serverUrl, status, requesting, allowed ->
            _uiState.update { state ->
                state.copy(
                    displayName = user?.displayName ?: "",
                    accountName = user?.accountName ?: "",
                    isAdmin = user?.isAdmin ?: false,
                    serverUrl = serverUrl,
                    connectionStatus = status,
                    requestingProfiles = requesting.map { 
                        RequestingProfile(it.accountName, it.avatarModel) 
                    },
                    allowedProfiles = allowed.map { 
                        AllowedProfile(it.displayName, it.accountName, it.avatarModel) 
                    }
                )
            }
        }.launchIn(viewModelScope)
    }

    fun dismissDialog() {
        _uiState.update {
            it.copy(
                dialogType = null,
                selectedAllowedProfile = null,
                selectedRequestingProfile = null,
                nickname = ""
            )
        }
    }

    fun showChangeServerDialog() {
        _uiState.update {
            it.copy(dialogType = DialogType.Server)
        }
    }

    fun changeServer() {
        viewModelScope.launch {
            userRepository.signOut()
            serverRepository.disconnect()
            serverRepository.saveServerUrl("")
            _uiState.update { it.copy(dialogType = null) }
        }
    }

    fun showSignOutDialog() {
        _uiState.update {
            it.copy(dialogType = DialogType.SignOut)
        }
    }

    fun signOut() {
        viewModelScope.launch {
            userRepository.signOut()
            _uiState.update { it.copy(dialogType = null) }
        }
    }

    fun showAssignNicknameDialog(profile: RequestingProfile) {
        _uiState.update {
            it.copy(
                dialogType = DialogType.AssignNickname,
                selectedRequestingProfile = profile
            )
        }
    }

    fun setNickname(nickname: String) {
        _uiState.update { it.copy(nickname = nickname) }
    }

    fun allowProfile() {
        val selectedProfile = _uiState.value.selectedRequestingProfile ?: return
        val nickname = _uiState.value.nickname
        viewModelScope.launch {
            adminRepository.allowProfile(selectedProfile.accountName, nickname)
            dismissDialog()
        }
    }

    fun showDenyProfileDialog(profile: RequestingProfile) {
        _uiState.update {
            it.copy(
                dialogType = DialogType.DenyProfile,
                selectedRequestingProfile = profile
            )
        }
    }

    fun denyProfile() {
        val selectedProfile = _uiState.value.selectedRequestingProfile ?: return
        viewModelScope.launch {
            adminRepository.denyProfile(selectedProfile.accountName)
            dismissDialog()
        }
    }

    fun showRemoveProfileDialog(profile: AllowedProfile) {
        _uiState.update {
            it.copy(
                dialogType = DialogType.RemoveProfile,
                selectedAllowedProfile = profile
            )
        }
    }

    fun removeProfile() {
        val selectedProfile = _uiState.value.selectedAllowedProfile ?: return
        viewModelScope.launch {
            adminRepository.removeProfile(selectedProfile.accountName)
            dismissDialog()
        }
    }
}
