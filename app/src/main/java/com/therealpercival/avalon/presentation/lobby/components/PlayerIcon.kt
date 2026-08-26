package com.therealpercival.avalon.presentation.lobby.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.therealpercival.avalon.R
import com.therealpercival.avalon.presentation.ui.theme.DayNightPreviews
import com.therealpercival.avalon.presentation.ui.theme.ThemePreview

@Composable
fun PlayerIcon(
    avatarModel: Any,
    displayName: String
) {
    Column(
        modifier = Modifier.width(64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = avatarModel,
            contentDescription = "$displayName's avatar",
            placeholder = ColorPainter(Color.LightGray),
            error = ColorPainter(Color.LightGray),
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )
        Text(
            text = displayName,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

@DayNightPreviews
@Composable
private fun PlayerIconPreview() {
    ThemePreview {
        PlayerIcon(
            avatarModel = R.drawable.x,
            displayName = "Drew"
        )
    }
}
