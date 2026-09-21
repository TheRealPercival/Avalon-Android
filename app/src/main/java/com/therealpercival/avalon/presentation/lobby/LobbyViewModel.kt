package com.therealpercival.avalon.presentation.lobby

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.therealpercival.avalon.domain.model.AvalonCharacter
import com.therealpercival.avalon.domain.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LobbyViewModel @Inject constructor(
    private val gameRepository: GameRepository
) : ViewModel() {
    data class Player(
        val displayName: String,
        val avatarModel: Any
    )

    data class UiState(
        val selectedPreset: String = "",
        val presetOptions: List<String> = emptyList(),
        val isPresetDropdownExpanded: Boolean = false,
        val selectedCharacters: List<AvalonCharacter> = emptyList(),
        val isShowingCharacterSheet: Boolean = false,
        val players: List<Player> = emptyList()
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            gameRepository.getLobbyData().collect { lobbyData ->
                _uiState.update {
                    it.copy(
                        selectedPreset = lobbyData.selectedPreset,
                        presetOptions = lobbyData.presetOptions,
                        selectedCharacters = lobbyData.selectedCharacters,
                        players = lobbyData.players.map { player ->
                            Player(
                                displayName = player.displayName,
                                avatarModel = player.avatarModel
                            )
                        }
                    )
                }
            }
        }
    }
}
