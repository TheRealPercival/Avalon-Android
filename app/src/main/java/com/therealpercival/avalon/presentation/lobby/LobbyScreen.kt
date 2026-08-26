package com.therealpercival.avalon.presentation.lobby

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.therealpercival.avalon.R
import com.therealpercival.avalon.presentation.components.DropdownInputField
import com.therealpercival.avalon.presentation.lobby.components.LadyOfTheLakeButton
import com.therealpercival.avalon.presentation.lobby.components.PlayersSection
import com.therealpercival.avalon.presentation.lobby.components.TrapperButton
import com.therealpercival.avalon.presentation.lobby.components.VoteResetButton
import com.therealpercival.avalon.presentation.ui.theme.DayNightDevicePreviews
import com.therealpercival.avalon.presentation.ui.theme.DeviceThemePreview

@Composable
fun LobbyScreen(
    viewModel: LobbyViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LobbyContent(
        state = uiState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LobbyContent(
    state: LobbyViewModel.UiState
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Game") },
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                        contentDescription = "Back"
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DropdownInputField(
                value = state.selectedPreset,
                onValueChange = { },
                options = state.presetOptions,
                label = "Preset",
                isExpanded = state.isPresetDropdownExpanded,
                onExpandedChange = { }
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.labelSmall
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TrapperButton()
                    LadyOfTheLakeButton()
                    VoteResetButton()
                }
            }
            PlayersSection(
                players = state.players
            )
        }
    }
}

@DayNightDevicePreviews
@Composable
private fun LobbyScreenPreview() {
    DeviceThemePreview {
        LobbyContent(
            state = LobbyViewModel.UiState(
                selectedPreset = "Classic",
                presetOptions = listOf("Classic"),
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
        )
    }
}
