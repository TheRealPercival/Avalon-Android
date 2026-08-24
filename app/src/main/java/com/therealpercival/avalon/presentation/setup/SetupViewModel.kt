package com.therealpercival.avalon.presentation.setup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.therealpercival.avalon.domain.repository.ServerRepository
import com.therealpercival.avalon.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

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
        val serverUrlState: ServerUrlState = ServerUrlState.Unvalidated
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _navigateToJoin = MutableSharedFlow<Unit>()
    val navigateToJoin: SharedFlow<Unit> = _navigateToJoin.asSharedFlow()

    init {
        viewModelScope.launch {
            serverRepository.getServerUrl().collect { url ->
                _uiState.update { it.copy(serverUrl = url) }
            }
        }
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
        viewModelScope.launch {
            _uiState.update { it.copy(serverUrlState = ServerUrlState.Fetching) }
            val isValid = serverRepository.validateServerUrl(_uiState.value.serverUrl)
            delay(1.seconds)
            if (isValid) {
                serverRepository.saveServerUrl(_uiState.value.serverUrl)
                _uiState.update { it.copy(serverUrlState = ServerUrlState.Valid) }
            } else {
                _uiState.update { it.copy(serverUrlState = ServerUrlState.Error) }
            }
        }
    }

    fun signIn() {
        viewModelScope.launch {
            userRepository.signIn()
            _navigateToJoin.emit(Unit)
        }
    }
}
