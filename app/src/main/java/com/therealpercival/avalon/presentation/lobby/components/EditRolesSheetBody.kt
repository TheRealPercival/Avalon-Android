package com.therealpercival.avalon.presentation.lobby.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.therealpercival.avalon.domain.model.AvalonCharacter
import com.therealpercival.avalon.domain.model.allCharacters
import com.therealpercival.avalon.presentation.components.CharacterCard
import com.therealpercival.avalon.presentation.components.CharacterTile
import com.therealpercival.avalon.presentation.components.DropdownInputField
import com.therealpercival.avalon.presentation.ui.theme.DayNightPreviews
import com.therealpercival.avalon.presentation.ui.theme.ThemePreview

@Composable
fun EditRolesSheetBody(
    selectedCharacters: List<AvalonCharacter>,
    selectedAssassin: AvalonCharacter? = null,
    isAssassinDropdownExpanded: Boolean = false,
    onCharacterCardClicked: (AvalonCharacter) -> Unit = { },
    onAssassinSelected: (AvalonCharacter) -> Unit = { },
    onAssassinDropdownExpandedChange: (Boolean) -> Unit = { }
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Edit Roles",
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.titleLarge
        )

        TeamSection(
            isGood = true,
            selectedCharacters = selectedCharacters,
            onCharacterCardClicked = onCharacterCardClicked
        )

        TeamSection(
            isGood = false,
            selectedCharacters = selectedCharacters,
            onCharacterCardClicked = onCharacterCardClicked
        )

        if (!selectedCharacters.contains(AvalonCharacter.Assassin)) {
            val evilCharacters = selectedCharacters.filter { !it.isGood }
            DropdownInputField(
                value = selectedAssassin?.name ?: "None",
                onValueChange = { selectedName ->
                    evilCharacters.find { it.name == selectedName }?.let { character ->
                        onAssassinSelected(character)
                    }
                },
                options = evilCharacters.map { it.name },
                modifier = Modifier.padding(horizontal = 16.dp),
                label = "Assassin",
                isExpanded = isAssassinDropdownExpanded,
                onExpandedChange = onAssassinDropdownExpandedChange
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
private fun TeamSection(
    isGood: Boolean,
    selectedCharacters: List<AvalonCharacter>,
    onCharacterCardClicked: (AvalonCharacter) -> Unit = { }
) {
    val sectionAvailableCharacters = allCharacters.filter { it.isGood == isGood }
    val sectionSelectedCharacters = selectedCharacters.filter { it.isGood == isGood }
    val maxCharacters = if (isGood) 6 else 4

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = if (isGood) "Good Team" else "Evil Team",
            style = MaterialTheme.typography.labelSmall
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(6) { index ->
                val tileModifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                if (index < maxCharacters) {
                    val character = sectionSelectedCharacters.getOrNull(index)
                    if (character != null) {
                        CharacterTile(
                            character = character,
                            modifier = tileModifier
                        )
                    } else {
                        AddRoleTile(
                            modifier = tileModifier
                        )
                    }
                } else {
                    Spacer(modifier = tileModifier)
                }
            }
        }
    }

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(count = 1) {
            Spacer(modifier = Modifier.width(8.dp))
        }
        items(sectionAvailableCharacters) { character ->
            CharacterCard(
                character = character,
                modifier = Modifier.width(128.dp),
                onClick = {
                    if (
                        (sectionSelectedCharacters.size < maxCharacters && character !in sectionSelectedCharacters)
                        || character in sectionSelectedCharacters
                    ) {
                        onCharacterCardClicked(character)
                    }
                },
                isFaded = character in sectionSelectedCharacters
            )
        }
        items(count = 1) {
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
@DayNightPreviews
private fun EditRolesSheetBodyPreview1() {
    ThemePreview {
        Column {
            EditRolesSheetBody(
                selectedCharacters = listOf(
                    AvalonCharacter.Merlin,
                    AvalonCharacter.Percival,
                    AvalonCharacter.LoyalServantOfArthur1,
                    AvalonCharacter.Morgana,
                    AvalonCharacter.Mordred
                )
            )
        }
    }
}

@Composable
@DayNightPreviews
private fun EditRolesSheetBodyPreview2() {
    ThemePreview {
        EditRolesSheetBody(
            selectedCharacters = listOf(
                AvalonCharacter.Merlin,
                AvalonCharacter.Percival,
                AvalonCharacter.LoyalServantOfArthur1,
                AvalonCharacter.LoyalServantOfArthur2,
                AvalonCharacter.LoyalServantOfArthur3,
                AvalonCharacter.LoyalServantOfArthur4,
                AvalonCharacter.Morgana,
                AvalonCharacter.Mordred,
                AvalonCharacter.MinionOfMordred1,
                AvalonCharacter.MinionOfMordred2
            )
        )
    }
}
