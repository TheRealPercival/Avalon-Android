package com.therealpercival.avalon.presentation.join

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.therealpercival.avalon.domain.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinViewModel @Inject constructor(
    private val gameRepository: GameRepository
) : ViewModel() {
    data class UiState(
        val joinText: String = "",
        val imageModels: List<Any> = emptyList(),
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            gameRepository.getGameSummary().collect { summary ->
                _uiState.update { 
                    it.copy(
                        joinText = summary.joinText,
                        imageModels = summary.playerAvatars
                    )
                }
            }
        }
    }
}
