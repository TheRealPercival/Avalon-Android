package com.therealpercival.avalon.presentation.lobby.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.therealpercival.avalon.domain.model.AvalonCharacter
import com.therealpercival.avalon.presentation.ui.theme.DayNightPreviews
import com.therealpercival.avalon.presentation.ui.theme.ThemePreview

@Composable
fun EditRolesSheetBody(
    selectedCharacters: List<AvalonCharacter>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Edit Roles",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "Good Team",
            style = MaterialTheme.typography.labelSmall
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val goodCharacters = selectedCharacters.filter { it.isGood }
            repeat(6) { index ->
                val tileModifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                val character = goodCharacters.getOrNull(index)
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
            }
        }

        Text(
            text = "Evil Team",
            style = MaterialTheme.typography.labelSmall
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val evilCharacters = selectedCharacters.filter { !it.isGood }
            repeat(6) { index ->
                val tileModifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                if (index < 4) {
                    val character = evilCharacters.getOrNull(index)
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
