package com.therealpercival.avalon.presentation.join.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.therealpercival.avalon.R
import com.therealpercival.avalon.presentation.ui.theme.DayNightPreviews
import com.therealpercival.avalon.presentation.ui.theme.ThemePreview

@Composable
fun AvatarStack(
    models: List<Any>,
    modifier: Modifier = Modifier,
    avatarSize: Dp = 24.dp,
    placeholder: Painter? = null
) {
    val overlapFactor = 0.5f

    Layout(
        modifier = modifier,
        content = {
            models.forEachIndexed { index, model ->
                AsyncImage(
                    model = model,
                    contentDescription = null,
                    placeholder = placeholder,
                    error = placeholder,
                    modifier = Modifier
                        .size(avatarSize)
                        .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
                        .drawWithContent {
                            drawContent()
                            if (index != 0) {
                                drawCircle(
                                    color = Color.Black,
                                    radius = (size.width / 2) * 1.15f,
                                    center = Offset(x = 0f, y = size.height / 2),
                                    blendMode = BlendMode.Clear
                                )
                            }
                        }
                        .clip(CircleShape)
                )
            }
        }
    ) { measurables, constraints ->
        val placeables = measurables.map { it.measure(constraints) }
        val width = if (placeables.isEmpty()) 0 else {
            val singleWidth = placeables.first().width
            (singleWidth + (placeables.size - 1) * singleWidth * (1f - overlapFactor)).toInt()
        }
        val height = placeables.maxOfOrNull { it.height } ?: 0

        layout(width, height) {
            var xPosition = 0
            placeables.forEach { placeable ->
                placeable.placeRelative(xPosition, 0)
                xPosition += (placeable.width * (1f - overlapFactor)).toInt()
            }
        }
    }
}

@DayNightPreviews
@Composable
private fun AvatarStackPreview() {
    ThemePreview {
        AvatarStack(
            models = listOf(
                R.drawable.x,
                R.drawable.benjson,
                R.drawable._shoe_
            ),
            avatarSize = 100.dp
        )
    }
}
