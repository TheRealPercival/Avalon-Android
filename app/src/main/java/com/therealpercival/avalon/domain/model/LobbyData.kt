package com.therealpercival.avalon.domain.model

data class LobbyData(
    val selectedPreset: String,
    val selectedCharacters: List<AvalonCharacter>,
    val presetOptions: List<String>,
    val players: List<AllowedProfile>
)
