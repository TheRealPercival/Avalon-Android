package com.therealpercival.avalon.presentation.lobby.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.therealpercival.avalon.domain.model.AvalonCharacter
import com.therealpercival.avalon.presentation.ui.theme.DayNightPreviews
import com.therealpercival.avalon.presentation.ui.theme.ThemePreview

@Composable
fun RolesSection(
    selectedCharacters: List<AvalonCharacter>,
    onClick: () -> Unit = { }
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = null,
                indication = null
            ) { onClick() },
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Roles",
            style = MaterialTheme.typography.labelSmall
        )

        val totalSlots = 10
        val rowSize = 5
        (0 until totalSlots).chunked(rowSize).forEach { rowIndices ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rowIndices.forEach { index ->
                    val character = selectedCharacters.getOrNull(index)
                    val tileModifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
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
        }
    }
}

@DayNightPreviews
@Composable
private fun RolesSectionPreview() {
    ThemePreview {
        RolesSection(
            selectedCharacters = listOf(
                AvalonCharacter.Merlin,
                AvalonCharacter.Percival,
                AvalonCharacter.LoyalServantOfArthur1,
                AvalonCharacter.LoyalServantOfArthur2,
                AvalonCharacter.LoyalServantOfArthur3,
                AvalonCharacter.Morgana,
                AvalonCharacter.Mordred,
                AvalonCharacter.MinionOfMordred1
            )
        )
    }
}

@DayNightPreviews
@Composable
private fun RolesSectionOneLinePreview() {
    ThemePreview {
        RolesSection(
            selectedCharacters = listOf(
                AvalonCharacter.Merlin,
                AvalonCharacter.Percival,
                AvalonCharacter.Morgana,
                AvalonCharacter.Mordred
            )
        )
    }
}

@DayNightPreviews
@Composable
private fun RolesSectionEmptyPreview() {
    ThemePreview {
        RolesSection(
            selectedCharacters = emptyList()
        )
    }
}
