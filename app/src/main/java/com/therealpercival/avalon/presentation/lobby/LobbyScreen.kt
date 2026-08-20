package com.therealpercival.avalon.presentation.lobby

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
            Text(
                text = "Lobby",
                style = MaterialTheme.typography.headlineSmall
            )
            DropdownInputField(
                value = state.selectedPreset,
                onValueChange = { },
                options = state.presetOptions,
                label = "Preset",
                isExpanded = state.isPresetDropdownExpanded,
                onExpandedChange = { }
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
                presetOptions = listOf("Classic")
            )
        )
    }
}
