package com.therealpercival.avalon.presentation.lobby.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
    account: Player? = null,
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
                isSelected = player == selectedPlayer,
                isYou = account == player,
                isEnabled = account != player,
                onClick = {
                    if (player != account) {
                        onPlayerClick(player)
                    }
                },
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        if (selectedPlayer != null) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Button(
                    onClick = { },
                    modifier = Modifier.weight(1f),
                ) {
                    Text(text = "Make Host")
                }
                Button(
                    onClick = { },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError,
                    ),
                ) {
                    Text(text = "Kick")
                }
            }
        }
    }
}

@DayNightPreviews
@Composable
private fun JoinedPlayersSheetBodyPreview() {
    ThemePreview {
        JoinedPlayersSheetBody(
            account = Player(
                displayName = "Drew",
                avatarModel = R.drawable.x,
            ),
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

@DayNightPreviews
@Composable
private fun JoinedPlayersSheetBodySelectedPreview() {
    ThemePreview {
        JoinedPlayersSheetBody(
            account = Player(
                displayName = "Drew",
                avatarModel = R.drawable.x,
            ),
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
            selectedPlayer = Player(
                displayName = "Ben",
                avatarModel = R.drawable.benjson,
            ),
        )
    }
}
