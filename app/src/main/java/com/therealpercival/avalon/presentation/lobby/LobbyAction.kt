package com.therealpercival.avalon.presentation.lobby

import com.therealpercival.avalon.domain.model.AvalonCharacter

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
