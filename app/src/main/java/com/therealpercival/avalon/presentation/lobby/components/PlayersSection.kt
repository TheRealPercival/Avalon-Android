package com.therealpercival.avalon.presentation.lobby.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.therealpercival.avalon.R
import com.therealpercival.avalon.presentation.lobby.LobbyViewModel
import com.therealpercival.avalon.presentation.ui.theme.DayNightPreviews
import com.therealpercival.avalon.presentation.ui.theme.ThemePreview

@Composable
fun PlayersSection(
    players: List<LobbyViewModel.Player>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Players",
            style = MaterialTheme.typography.labelSmall
        )
        players.chunked(5).forEach { rowPlayers ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                rowPlayers.forEach { player ->
                    PlayerIcon(
                        avatarModel = player.avatarModel,
                        displayName = player.displayName
                    )
                }
                repeat(5 - rowPlayers.size) {
                    Spacer(modifier = Modifier.width(64.dp))
                }
            }
        }
    }
}

@DayNightPreviews
@Composable
private fun PlayersSectionPreview() {
    ThemePreview {
        PlayersSection(
            players = listOf(
                LobbyViewModel.Player(
                    displayName = "Drew",
                    avatarModel = R.drawable.x
                ),
                LobbyViewModel.Player(
                    displayName = "Ben",
                    avatarModel = R.drawable.benjson
                ),
                LobbyViewModel.Player(
                    displayName = "Izzy",
                    avatarModel = R.drawable.izzyderose
                ),
                LobbyViewModel.Player(
                    displayName = "Thomas",
                    avatarModel = R.drawable._shoe_
                ),
                LobbyViewModel.Player(
                    displayName = "Landon",
                    avatarModel = R.drawable.landon248
                )
            )
        )
    }
}

@DayNightPreviews
@Composable
private fun PlayersSectionPreview2() {
    ThemePreview {
        PlayersSection(
            players = listOf(
                LobbyViewModel.Player(
                    displayName = "Drew",
                    avatarModel = R.drawable.x
                ),
                LobbyViewModel.Player(
                    displayName = "Ben",
                    avatarModel = R.drawable.benjson
                ),
                LobbyViewModel.Player(
                    displayName = "Izzy",
                    avatarModel = R.drawable.izzyderose
                ),
                LobbyViewModel.Player(
                    displayName = "Thomas",
                    avatarModel = R.drawable._shoe_
                ),
                LobbyViewModel.Player(
                    displayName = "Landon",
                    avatarModel = R.drawable.landon248
                ),
                LobbyViewModel.Player(
                    displayName = "Drew",
                    avatarModel = R.drawable.x
                ),
                LobbyViewModel.Player(
                    displayName = "Ben",
                    avatarModel = R.drawable.benjson
                ),
                LobbyViewModel.Player(
                    displayName = "Izzy",
                    avatarModel = R.drawable.izzyderose
                )
            )
        )
    }
}
