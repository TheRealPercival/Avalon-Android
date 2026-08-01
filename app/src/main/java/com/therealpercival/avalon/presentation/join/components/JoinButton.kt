package com.therealpercival.avalon.presentation.join.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import com.therealpercival.avalon.R
import com.therealpercival.avalon.presentation.ui.theme.DayNightPreviews
import com.therealpercival.avalon.presentation.ui.theme.ThemePreview

@Composable
fun JoinButton(
    text: String,
    imageModels: List<Any>,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    placeholder: Painter = ColorPainter(Color.LightGray)
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = text)
        Spacer(modifier = Modifier.weight(1f))
        AvatarStack(
            models = imageModels,
            placeholder = placeholder
        )
    }
}

@DayNightPreviews
@Composable
fun JoinButtonPreview() {
    ThemePreview {
        JoinButton(
            text = "Join game (5 in lobby)",
            imageModels = listOf(
                R.drawable.x,
                R.drawable.benjson,
                R.drawable._shoe_
            )
        )
    }
}
