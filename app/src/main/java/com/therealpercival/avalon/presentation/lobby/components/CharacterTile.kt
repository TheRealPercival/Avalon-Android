package com.therealpercival.avalon.presentation.lobby.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.therealpercival.avalon.domain.model.AvalonCharacter
import com.therealpercival.avalon.presentation.ui.theme.DayNightPreviews
import com.therealpercival.avalon.presentation.ui.theme.ThemePreview

@Composable
fun CharacterTile(
    character: AvalonCharacter,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = character.tileImage,
        contentDescription = character.name,
        modifier = modifier
            .clip(shape = MaterialTheme.shapes.medium)
    )
}

@Composable
@DayNightPreviews
private fun CharacterTilePreview() {
    ThemePreview {
        CharacterTile(
            character = AvalonCharacter.Percival,
            modifier = Modifier.size(64.dp)
        )
    }
}
