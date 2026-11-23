package com.therealpercival.avalon.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
    modifier: Modifier = Modifier,
    isAssassin: Boolean = false,
) {
    Box(
        modifier = modifier,
    ) {
        AsyncImage(
            model = character.tileImage,
            contentDescription = character.name,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = MaterialTheme.shapes.medium),
        )
        if (isAssassin) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopStart,
            ) {
                Text(
                    text = "🗡️",
                    modifier = Modifier.offset(x = (-6).dp, y = (-6).dp),
                )
            }
        }
    }
}

@Composable
@DayNightPreviews
private fun CharacterTilePreview() {
    ThemePreview {
        CharacterTile(
            character = AvalonCharacter.Percival,
            modifier = Modifier.size(64.dp),
        )
    }
}

@Composable
@DayNightPreviews
private fun CharacterTileAssassinPreview() {
    ThemePreview {
        CharacterTile(
            character = AvalonCharacter.Morgana,
            isAssassin = true,
            modifier = Modifier.size(64.dp),
        )
    }
}
