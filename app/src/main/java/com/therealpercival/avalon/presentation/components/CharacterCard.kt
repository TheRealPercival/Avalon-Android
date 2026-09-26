package com.therealpercival.avalon.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import coil3.compose.AsyncImage
import com.therealpercival.avalon.domain.model.AvalonCharacter
import com.therealpercival.avalon.presentation.ui.conditional
import com.therealpercival.avalon.presentation.ui.theme.DayNightPreviews
import com.therealpercival.avalon.presentation.ui.theme.ThemePreview

@Composable
fun CharacterCard(
    character: AvalonCharacter,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    isFaded: Boolean = false
) {
    AsyncImage(
        model = character.cardImage,
        contentDescription = character.name,
        modifier = modifier
            .clip(shape = MaterialTheme.shapes.medium)
            .clickable {
                onClick()
            }
            .conditional(isFaded) {
                alpha(0.5f)
            }
    )
}

@Composable
@DayNightPreviews
private fun CharacterCardPreview() {
    ThemePreview {
        CharacterCard(
            character = AvalonCharacter.Percival
        )
    }
}
