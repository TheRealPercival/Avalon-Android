package com.therealpercival.avalon.domain.model

import com.therealpercival.avalon.presentation.lobby.Preset

data class LobbyData(
    val selectedPreset: Preset,
    val selectedCharacters: List<AvalonCharacter>,
    val presetOptions: List<Preset>,
    val account: AllowedProfile,
    val players: List<AllowedProfile>,
)
