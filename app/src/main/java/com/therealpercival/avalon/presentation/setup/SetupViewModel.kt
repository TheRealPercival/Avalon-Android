package com.therealpercival.avalon.presentation.setup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.therealpercival.avalon.domain.model.ConnectionStatus
import com.therealpercival.avalon.domain.repository.ServerRepository
import com.therealpercival.avalon.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetupViewModel @Inject constructor(
    private val serverRepository: ServerRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    sealed class ServerUrlState {
        object Unvalidated : ServerUrlState()
        object Fetching : ServerUrlState()
        object Valid : ServerUrlState()
        object Error : ServerUrlState()
    }
    data class UiState(
        val serverUrl: String = "",
        val serverUrlState: ServerUrlState = ServerUrlState.Unvalidated,
        val connectionStatus: ConnectionStatus = ConnectionStatus.DISCONNECTED,
        val isServerInfoLoaded: Boolean = false,
        val isAuthenticating: Boolean = false
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _navigateToJoin = MutableSharedFlow<Unit>()
    val navigateToJoin: SharedFlow<Unit> = _navigateToJoin.asSharedFlow()

    init {
        serverRepository.getServerUrl()
            .onEach { url -> _uiState.update { it.copy(serverUrl = url) } }
            .launchIn(viewModelScope)
        
        combine(
            serverRepository.getConnectionStatus(),
            serverRepository.getServerInfo()
        ) { status, info ->
            _uiState.update { it.copy(
                connectionStatus = status,
                isServerInfoLoaded = info != null
            ) }
        }.launchIn(viewModelScope)

        userRepository.isAuthenticating()
            .onEach { authenticating ->
                _uiState.update { it.copy(isAuthenticating = authenticating) }
            }
            .launchIn(viewModelScope)

        userRepository.getCurrentUser()
            .onEach { user ->
                if (user != null) {
                    _navigateToJoin.emit(Unit)
                }
            }
            .launchIn(viewModelScope)
    }
    
    companion object {
        const val TAG = "SetupViewModel"
    }

    fun setServerUrl(url: String) {
        _uiState.update { 
            it.copy(
                serverUrl = url,
                serverUrlState = ServerUrlState.Unvalidated
            ) 
        }
    }

    fun connectToServer() {
        Log.d(TAG, "Connect to server clicked: ${uiState.value.serverUrl}")
        viewModelScope.launch {
            _uiState.update { it.copy(serverUrlState = ServerUrlState.Fetching) }
            val isValid = serverRepository.validateServerUrl(_uiState.value.serverUrl)
            Log.d(TAG, "Server URL validation result: $isValid")
            if (isValid) {
                serverRepository.saveServerUrl(_uiState.value.serverUrl)
                _uiState.update { it.copy(serverUrlState = ServerUrlState.Valid) }
                Log.d(TAG, "Connecting to server...")
                serverRepository.connect()
            } else {
                _uiState.update { it.copy(serverUrlState = ServerUrlState.Error) }
            }
        }
    }

    fun signIn() {
        viewModelScope.launch {
            userRepository.signIn()
        }
    }
}
