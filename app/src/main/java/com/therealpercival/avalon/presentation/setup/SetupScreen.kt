package com.therealpercival.avalon.presentation.setup

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.therealpercival.avalon.R
import com.therealpercival.avalon.presentation.NoRippleInteractionSource
import com.therealpercival.avalon.presentation.setup.components.DiscordSignInSection
import com.therealpercival.avalon.presentation.setup.components.ServerUrlSection
import com.therealpercival.avalon.presentation.ui.theme.DayNightDevicePreviews
import com.therealpercival.avalon.presentation.ui.theme.DeviceThemePreview

@Composable
fun SetupScreen(
    viewModel: SetupViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()
    SetupContent(
        state = state,
        onServerUrlChange = viewModel::setServerUrl,
        onConnectClicked = viewModel::connectToServer
    )
}

@Composable
fun SetupContent(
    state: SetupViewModel.UiState,
    onServerUrlChange: (String) -> Unit = { },
    onConnectClicked: () -> Unit = { }
) {
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(state = scrollState)
            .imePadding()
            .navigationBarsPadding()
            .clickable(
                interactionSource = NoRippleInteractionSource()
            ) {
                focusManager.clearFocus()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = spacedBy(12.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Image(
                painter = painterResource(id = R.drawable.merlin_logo),
                contentDescription = "Avalon logo",
                modifier = Modifier
                    .height(84.dp)
                    .align(Alignment.Bottom)
                    .padding(bottom = 6.dp)
            )
            Column(
                verticalArrangement = spacedBy((-10).dp)
            ) {
                Text(
                    text = "Avalon",
                    fontSize = 50.sp
                )
                Text(
                    text = "The Real Percival",
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        if (state.serverUrlState is SetupViewModel.ServerUrlState.Valid) {
            DiscordSignInSection(
                serverUrl = state.serverUrl
            )
        } else {
            ServerUrlSection(
                serverUrl = state.serverUrl,
                onServerUrlChange = onServerUrlChange
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        when (state.serverUrlState) {
            is SetupViewModel.ServerUrlState.Unvalidated -> {
                Button(
                    onClick = onConnectClicked,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Connect")
                }
            }

            is SetupViewModel.ServerUrlState.Fetching -> {
                Button(
                    onClick = onConnectClicked,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .height(16.dp)
                            .width(16.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }

            else -> { }
        }
    }
}

@DayNightDevicePreviews
@Composable
fun SetupScreenPreview() {
    DeviceThemePreview {
        SetupContent(
            state = SetupViewModel.UiState()
        )
    }
}

@DayNightDevicePreviews
@Composable
fun SetupScreenFetchingPreview() {
    DeviceThemePreview {
        SetupContent(
            state = SetupViewModel.UiState(
                serverUrlState = SetupViewModel.ServerUrlState.Fetching
            )
        )
    }
}

@DayNightDevicePreviews
@Composable
fun SetupScreenDiscordPreview() {
    DeviceThemePreview {
        SetupContent(
            state = SetupViewModel.UiState(
                serverUrl = "server.therealpercival.com",
                serverUrlState = SetupViewModel.ServerUrlState.Valid
            )
        )
    }
}
