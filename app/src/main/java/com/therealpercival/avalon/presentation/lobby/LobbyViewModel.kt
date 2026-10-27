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
    private val gameRepository: GameRepository,
) : ViewModel() {
    data class Player(
        val displayName: String,
        val avatarModel: Any,
    )

    private val _state = MutableStateFlow(LobbyState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            gameRepository.getLobbyData().collect { lobbyData ->
                _state.update {
                    it.copy(
                        selectedPreset = lobbyData.selectedPreset,
                        presetOptions = lobbyData.presetOptions,
                        selectedCharacters = lobbyData.selectedCharacters,
                        players = lobbyData.players.map { player ->
                            Player(
                                displayName = player.displayName,
                                avatarModel = player.avatarModel,
                            )
                        },
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
                _state.update {
                    it.copy(
                        selectedPreset = action.preset,
                        isPresetDropdownExpanded = false,
                    )
                }
            }

            is LobbyAction.PresetExpandedChange -> {
                _state.update {
                    it.copy(isPresetDropdownExpanded = action.isExpanded)
                }
            }

            is LobbyAction.RolesSectionClicked -> {
                _state.update { it.copy(isShowingCharacterSheet = true) }
            }

            is LobbyAction.CharacterSheetDismissed -> {
                _state.update {
                    it.copy(
                        isShowingCharacterSheet = false,
                        focusedCharacter = null,
                    )
                }
            }

            is LobbyAction.CharacterCardClicked -> {
                _state.update { state ->
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
                        selectedAssassin = newSelectedAssassin,
                    )
                }
            }

            is LobbyAction.CharacterCardLongClicked -> {
                _state.update { it.copy(focusedCharacter = action.character) }
            }

            is LobbyAction.FocusedCharacterDismissed -> {
                _state.update { it.copy(focusedCharacter = null) }
            }

            is LobbyAction.AssassinSelected -> {
                _state.update {
                    it.copy(
                        selectedAssassin = action.character,
                        isAssassinDropdownExpanded = false,
                    )
                }
            }

            is LobbyAction.AssassinDropdownExpandedChange -> {
                _state.update {
                    it.copy(isAssassinDropdownExpanded = action.isExpanded)
                }
            }

            is LobbyAction.TrapperClicked -> {
                _state.update { it.copy(isTrapperEnabled = !it.isTrapperEnabled) }
            }

            is LobbyAction.LadyOfTheLakeClicked -> {
                _state.update { it.copy(isLadyOfTheLakeEnabled = !it.isLadyOfTheLakeEnabled) }
            }

            is LobbyAction.VoteResetClicked -> {
                _state.update { it.copy(isVoteResetEnabled = !it.isVoteResetEnabled) }
            }
        }
    }
}
