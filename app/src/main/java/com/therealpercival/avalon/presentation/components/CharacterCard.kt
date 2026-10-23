package com.therealpercival.avalon.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharacterCard(
    character: AvalonCharacter,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    onLongClick: (() -> Unit)? = null,
    isFaded: Boolean = false
) {
    AsyncImage(
        model = character.cardImage,
        contentDescription = character.name,
        modifier = modifier
            .clip(shape = MaterialTheme.shapes.medium)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick,
            )
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
