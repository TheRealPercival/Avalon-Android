package com.therealpercival.avalon.presentation.lobby.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.therealpercival.avalon.R
import com.therealpercival.avalon.presentation.ui.theme.DayNightPreviews
import com.therealpercival.avalon.presentation.ui.theme.ThemePreview

@Composable
fun VoteResetButton(
    isSelected: Boolean = false
) {
    val backgroundColor =
        if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
    val tintColor =
        if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant

    Box(
        modifier = Modifier
            .background(color = backgroundColor, shape = MaterialTheme.shapes.medium)
            .size(64.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_thumb_down_24),
            contentDescription = "Vote reset rule",
            tint = tintColor,
            modifier = Modifier.size(16.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.baseline_refresh_24),
            contentDescription = null,
            tint = tintColor,
            modifier = Modifier
                .size(40.dp)
                .scale(scaleX = -1f, scaleY = 1f)
                .rotate(degrees = 270f)
        )
    }
}

@DayNightPreviews
@Composable
private fun VoteResetButtonPreview() {
    ThemePreview {
        VoteResetButton()
    }
}

@DayNightPreviews
@Composable
private fun VoteResetButtonSelectedPreview() {
    ThemePreview {
        VoteResetButton(
            isSelected = true
        )
    }
}
