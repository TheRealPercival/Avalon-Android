package com.therealpercival.avalon.presentation.setup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.therealpercival.avalon.presentation.NoRippleInteractionSource
import com.therealpercival.avalon.presentation.components.AvalonLogo
import com.therealpercival.avalon.presentation.setup.components.DiscordSignInSection
import com.therealpercival.avalon.presentation.setup.components.ServerUrlSection
import com.therealpercival.avalon.presentation.ui.theme.DayNightDevicePreviews
import com.therealpercival.avalon.presentation.ui.theme.DeviceThemePreview
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SetupScreen(
    viewModel: SetupViewModel = hiltViewModel(),
    onSignInSuccess: () -> Unit = { }
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.navigateToJoin.collectLatest {
            onSignInSuccess()
        }
    }

    SetupContent(
        state = state,
        onServerUrlChange = viewModel::setServerUrl,
        onConnectClicked = viewModel::connectToServer,
        onSignInClicked = viewModel::signIn
    )
}

@Composable
internal fun SetupContent(
    state: SetupViewModel.UiState,
    onServerUrlChange: (String) -> Unit = { },
    onConnectClicked: () -> Unit = { },
    onSignInClicked: () -> Unit = { }
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
        AvalonLogo()

        Spacer(modifier = Modifier.weight(1f))

        if (state.serverUrlState is SetupViewModel.ServerUrlState.Valid) {
            DiscordSignInSection(
                serverUrl = state.serverUrl,
                onSignInClicked = onSignInClicked
            )
        } else {
            ServerUrlSection(
                serverUrl = state.serverUrl,
                isInputEnabled = state.serverUrlState !is SetupViewModel.ServerUrlState.Fetching,
                isError = state.serverUrlState is SetupViewModel.ServerUrlState.Error,
                onServerUrlChange = onServerUrlChange,
                onDone = onConnectClicked
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        if (state.serverUrlState !is SetupViewModel.ServerUrlState.Valid) {
            Button(
                onClick = onConnectClicked,
                modifier = Modifier.fillMaxWidth()
                    .testTag("SetupScreen_ConnectButton"),
                enabled = state.serverUrl.isNotBlank() && state.serverUrlState is SetupViewModel.ServerUrlState.Unvalidated,
            ) {
                if (state.serverUrlState in listOf(
                        SetupViewModel.ServerUrlState.Unvalidated,
                        SetupViewModel.ServerUrlState.Error
                    )
                ) {
                    Text(text = "Connect")
                } else if (state.serverUrlState is SetupViewModel.ServerUrlState.Fetching) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .height(16.dp)
                            .width(16.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
        }
    }
}

@DayNightDevicePreviews
@Composable
private fun SetupScreenPreview() {
    DeviceThemePreview {
        SetupContent(
            state = SetupViewModel.UiState()
        )
    }
}

@DayNightDevicePreviews
@Composable
private fun SetupScreenFetchingPreview() {
    DeviceThemePreview {
        SetupContent(
            state = SetupViewModel.UiState(
                serverUrl = "server.therealpercival.com",
                serverUrlState = SetupViewModel.ServerUrlState.Fetching
            )
        )
    }
}

@DayNightDevicePreviews
@Composable
private fun SetupScreenErrorPreview() {
    DeviceThemePreview {
        SetupContent(
            state = SetupViewModel.UiState(
                serverUrl = "server.therealpercival.com",
                serverUrlState = SetupViewModel.ServerUrlState.Error
            )
        )
    }
}

@DayNightDevicePreviews
@Composable
private fun SetupScreenDiscordPreview() {
    DeviceThemePreview {
        SetupContent(
            state = SetupViewModel.UiState(
                serverUrl = "server.therealpercival.com",
                serverUrlState = SetupViewModel.ServerUrlState.Valid
            )
        )
    }
}
