package com.therealpercival.avalon.presentation.lobby.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.therealpercival.avalon.R
import com.therealpercival.avalon.presentation.lobby.Player
import com.therealpercival.avalon.presentation.ui.theme.DayNightPreviews
import com.therealpercival.avalon.presentation.ui.theme.ThemePreview

@Composable
fun PlayerItem(
    player: Player,
    onClick: () -> Unit = { },
    selected: Boolean = false,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = MaterialTheme.colorScheme.outline, shape = MaterialTheme.shapes.medium)
            .background(color = if (selected) MaterialTheme.colorScheme.primaryContainer else Color.Unspecified, shape = MaterialTheme.shapes.medium)
            .clip(MaterialTheme.shapes.medium)
            .clickable {
                onClick()
            }
            .padding(all = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        PlayerIcon(
            avatarModel = player.avatarModel,
            displayName = player.displayName,
            modifier = Modifier.size(32.dp),
        )

        Text(
            text = player.displayName,
            color = if (selected) MaterialTheme.colorScheme.onPrimaryContainer else Color.Unspecified,
        )
    }
}

@DayNightPreviews
@Composable
private fun PlayerItemPreview() {
    ThemePreview {
        PlayerItem(
            Player(
                displayName = "Drew",
                avatarModel = R.drawable.x,
            ),
        )
    }
}

@DayNightPreviews
@Composable
private fun PlayerItemSelectedPreview() {
    ThemePreview {
        PlayerItem(
            Player(
                displayName = "Drew",
                avatarModel = R.drawable.x,
            ),
            selected = true,
        )
    }
}
