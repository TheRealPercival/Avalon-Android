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
        val selectedAssassin: AvalonCharacter? = null,
        val isAssassinDropdownExpanded: Boolean = false,
        val isShowingCharacterSheet: Boolean = false,
        val isTrapperEnabled: Boolean = false,
        val isLadyOfTheLakeEnabled: Boolean = false,
        val isVoteResetEnabled: Boolean = false,
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

    fun onPresetSelected(preset: String) {
        _uiState.update {
            it.copy(
                selectedPreset = preset,
                isPresetDropdownExpanded = false
            )
        }
    }

    fun onPresetExpandedChange(isExpanded: Boolean) {
        _uiState.update {
            it.copy(isPresetDropdownExpanded = isExpanded)
        }
    }

    fun onAssassinSelected(character: AvalonCharacter) {
        _uiState.update {
            it.copy(
                selectedAssassin = character,
                isAssassinDropdownExpanded = false
            )
        }
    }

    fun onAssassinDropdownExpandedChange(isExpanded: Boolean) {
        _uiState.update {
            it.copy(isAssassinDropdownExpanded = isExpanded)
        }
    }

    fun onRolesSectionClicked() {
        _uiState.update { it.copy(isShowingCharacterSheet = true) }
    }

    fun onCharacterSheetDismissed() {
        _uiState.update { it.copy(isShowingCharacterSheet = false) }
    }

    fun onCharacterCardClicked(character: AvalonCharacter) {
        _uiState.update { state ->
            val newSelectedCharacters = state.selectedCharacters.toMutableList()
            if (newSelectedCharacters.contains(character)) {
                newSelectedCharacters.remove(character)
            } else {
                newSelectedCharacters.add(character)
            }
            val sortedCharacters = newSelectedCharacters.sortedBy { it.order }
            val newSelectedAssassin = if (
                (sortedCharacters.contains(AvalonCharacter.Assassin)) ||
                (state.selectedAssassin != null && !sortedCharacters.contains(state.selectedAssassin))
            ) {
                null
            } else {
                state.selectedAssassin
            }
            state.copy(
                selectedCharacters = sortedCharacters,
                selectedAssassin = newSelectedAssassin
            )
        }
    }

    fun onTrapperClicked() {
        _uiState.update { it.copy(isTrapperEnabled = !it.isTrapperEnabled) }
    }

    fun onLadyOfTheLakeClicked() {
        _uiState.update { it.copy(isLadyOfTheLakeEnabled = !it.isLadyOfTheLakeEnabled) }
    }

    fun onVoteResetClicked() {
        _uiState.update { it.copy(isVoteResetEnabled = !it.isVoteResetEnabled) }
    }
}
