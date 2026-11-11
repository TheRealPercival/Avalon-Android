package com.therealpercival.avalon.presentation.lobby

import com.therealpercival.avalon.domain.model.AvalonCharacter

data class LobbyState(
    val selectedPreset: String = "",
    val presetOptions: List<String> = emptyList(),
    val isPresetDropdownExpanded: Boolean = false,
    val selectedCharacters: List<AvalonCharacter> = emptyList(),
    val selectedAssassin: AvalonCharacter? = null,
    val isAssassinDropdownExpanded: Boolean = false,
    val isShowingCharacterSheet: Boolean = false,
    val isShowingPlayerSheet: Boolean = false,
    val focusedCharacter: AvalonCharacter? = null,
    val isTrapperEnabled: Boolean = false,
    val isLadyOfTheLakeEnabled: Boolean = false,
    val isVoteResetEnabled: Boolean = false,
    val players: List<Player> = emptyList(),
)

data class Player(
    val displayName: String,
    val avatarModel: Any,
)
