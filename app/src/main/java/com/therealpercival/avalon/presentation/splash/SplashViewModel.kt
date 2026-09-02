package com.therealpercival.avalon.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.therealpercival.avalon.domain.repository.ServerRepository
import com.therealpercival.avalon.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val serverRepository: ServerRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    sealed class SplashEvent {
        object NavigateToSetup : SplashEvent()
        object NavigateToJoin : SplashEvent()
    }

    private val _events = MutableSharedFlow<SplashEvent>()
    val events = _events.asSharedFlow()

    init {
        viewModelScope.launch {
            val url = serverRepository.getServerUrl().first()
            if (url.isBlank()) {
                _events.emit(SplashEvent.NavigateToSetup)
                return@launch
            }

            val serverInfo = withTimeoutOrNull(5.seconds) {
                serverRepository.getServerInfo().first { it != null }
            }

            if (serverInfo == null) {
                _events.emit(SplashEvent.NavigateToSetup)
                return@launch
            }

            withTimeoutOrNull(3.seconds) {
                delay(500.milliseconds)
                userRepository.getCurrentUser().first { user ->
                    user != null
                }
            }

            val user = userRepository.getCurrentUser().first()
            if (user != null) {
                _events.emit(SplashEvent.NavigateToJoin)
            } else {
                _events.emit(SplashEvent.NavigateToSetup)
            }
        }
    }
}
