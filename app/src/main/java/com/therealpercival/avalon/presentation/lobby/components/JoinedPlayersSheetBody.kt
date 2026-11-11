package com.therealpercival.avalon.presentation.lobby.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.therealpercival.avalon.R
import com.therealpercival.avalon.presentation.lobby.Player
import com.therealpercival.avalon.presentation.ui.theme.DayNightPreviews
import com.therealpercival.avalon.presentation.ui.theme.ThemePreview

@Composable
fun JoinedPlayersSheetBody(
    players: List<Player>,
    selectedPlayer: Player? = null,
    onPlayerClick: (Player) -> Unit = { },
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = "Joined Players",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.titleLarge,
        )

        players.forEach { player ->
            PlayerItem(
                player = player,
                selected = player == selectedPlayer,
                onClick = {
                    onPlayerClick(player)
                },
            )
        }
    }
}

@DayNightPreviews
@Composable
private fun JoinedPlayersSheetBodyPreview() {
    ThemePreview {
        JoinedPlayersSheetBody(
            players = listOf(
                Player(
                    displayName = "Drew",
                    avatarModel = R.drawable.x,
                ),
                Player(
                    displayName = "Ben",
                    avatarModel = R.drawable.benjson,
                ),
                Player(
                    displayName = "Izzy",
                    avatarModel = R.drawable.izzyderose,
                ),
                Player(
                    displayName = "Thomas",
                    avatarModel = R.drawable._shoe_,
                ),
                Player(
                    displayName = "Landon",
                    avatarModel = R.drawable.landon248,
                ),
            ),
        )
    }
}
