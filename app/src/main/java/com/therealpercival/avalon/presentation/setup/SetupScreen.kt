package com.therealpercival.avalon.presentation.setup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.therealpercival.avalon.presentation.NoRippleInteractionSource
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
            .clickable (
                interactionSource = NoRippleInteractionSource()
            ) {
                focusManager.clearFocus()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(81.dp)
                    .height(60.dp)
                    .background(MaterialTheme.colorScheme.onBackground)
            )
            Column {
                Text(text = "Avalon", fontSize = 50.sp)
                Text(text = "The Real Percival", fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = spacedBy(12.dp)
        ) {
            Text(
                text = "You've arrived in Avalon!",
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Welcome to an online adaptation of Don Eskridge's Avalon: Big Box Edition. Please enter your group's server URL below to begin.",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        OutlinedTextField(
            value = state.serverUrl,
            onValueChange = { onServerUrlChange(it) },
            modifier = Modifier.padding(12.dp),
            label = { Text("Server URL") }
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onConnectClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Connect")
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
