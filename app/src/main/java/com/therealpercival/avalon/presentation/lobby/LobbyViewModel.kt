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
    sealed interface LobbyAction {
        data object BackClicked : LobbyAction
        data object StartClicked : LobbyAction
        data class PresetSelected(val preset: String) : LobbyAction
        data class PresetExpandedChange(val isExpanded: Boolean) : LobbyAction
        data object RolesSectionClicked : LobbyAction
        data object CharacterSheetDismissed : LobbyAction
        data class CharacterCardClicked(val character: AvalonCharacter) : LobbyAction
        data class CharacterCardLongClicked(val character: AvalonCharacter) : LobbyAction
        data object FocusedCharacterDismissed : LobbyAction
        data class AssassinSelected(val character: AvalonCharacter) : LobbyAction
        data class AssassinDropdownExpandedChange(val isExpanded: Boolean) : LobbyAction
        data object TrapperClicked : LobbyAction
        data object LadyOfTheLakeClicked : LobbyAction
        data object VoteResetClicked : LobbyAction
    }

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
        val focusedCharacter: AvalonCharacter? = null,
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

    fun onAction(action: LobbyAction) {
        when (action) {
            is LobbyAction.BackClicked -> {}
            is LobbyAction.StartClicked -> {}
            is LobbyAction.PresetSelected -> {
                _uiState.update {
                    it.copy(
                        selectedPreset = action.preset,
                        isPresetDropdownExpanded = false
                    )
                }
            }

            is LobbyAction.PresetExpandedChange -> {
                _uiState.update {
                    it.copy(isPresetDropdownExpanded = action.isExpanded)
                }
            }

            is LobbyAction.RolesSectionClicked -> {
                _uiState.update { it.copy(isShowingCharacterSheet = true) }
            }

            is LobbyAction.CharacterSheetDismissed -> {
                _uiState.update {
                    it.copy(
                        isShowingCharacterSheet = false,
                        focusedCharacter = null
                    )
                }
            }

            is LobbyAction.CharacterCardClicked -> {
                _uiState.update { state ->
                    val newSelectedCharacters = state.selectedCharacters.toMutableList()
                    if (newSelectedCharacters.contains(action.character)) {
                        newSelectedCharacters.remove(action.character)
                    } else {
                        newSelectedCharacters.add(action.character)
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

            is LobbyAction.CharacterCardLongClicked -> {
                _uiState.update { it.copy(focusedCharacter = action.character) }
            }

            is LobbyAction.FocusedCharacterDismissed -> {
                _uiState.update { it.copy(focusedCharacter = null) }
            }

            is LobbyAction.AssassinSelected -> {
                _uiState.update {
                    it.copy(
                        selectedAssassin = action.character,
                        isAssassinDropdownExpanded = false
                    )
                }
            }

            is LobbyAction.AssassinDropdownExpandedChange -> {
                _uiState.update {
                    it.copy(isAssassinDropdownExpanded = action.isExpanded)
                }
            }

            is LobbyAction.TrapperClicked -> {
                _uiState.update { it.copy(isTrapperEnabled = !it.isTrapperEnabled) }
            }

            is LobbyAction.LadyOfTheLakeClicked -> {
                _uiState.update { it.copy(isLadyOfTheLakeEnabled = !it.isLadyOfTheLakeEnabled) }
            }

            is LobbyAction.VoteResetClicked -> {
                _uiState.update { it.copy(isVoteResetEnabled = !it.isVoteResetEnabled) }
            }
        }
    }
}
