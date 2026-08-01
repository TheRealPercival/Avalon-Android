package com.therealpercival.avalon.presentation.settings

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.therealpercival.avalon.R
import com.therealpercival.avalon.presentation.Screen
import com.therealpercival.avalon.presentation.ui.theme.AvalonNavBarThemePreview
import com.therealpercival.avalon.presentation.ui.theme.DayNightDevicePreviews

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()
    SettingsContent(
        state = state
    )
}

@Composable
private fun SettingsContent(
    state: SettingsViewModel.UiState,
    onChangeServerClicked: () -> Unit = { },
    onSignOutClicked: () -> Unit = { }
) {
    val disabledColors = OutlinedTextFieldDefaults.colors(
        disabledTextColor = MaterialTheme.colorScheme.onSurface,
        disabledBorderColor = MaterialTheme.colorScheme.outline,
        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledSuffixColor = MaterialTheme.colorScheme.onSurfaceVariant
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = state.serverUrl,
            onValueChange = { },
            enabled = false,
            label = { Text("Server URL") },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_check_24),
                    contentDescription = "Server URL is valid"
                )
            },
            singleLine = true,
            maxLines = 1,
            colors = disabledColors,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                onChangeServerClicked()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError
            )
        ) {
            Text(text = "Change Server")
        }
        OutlinedTextField(
            value = state.displayName,
            onValueChange = { },
            enabled = false,
            label = { Text("Account") },
            suffix = { Text(state.accountName) },
            singleLine = true,
            maxLines = 1,
            colors = disabledColors,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                onSignOutClicked()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError
            )
        ) {
            Text(text = "Sign Out")
        }
    }
}

@DayNightDevicePreviews
@Composable
private fun SettingsScreenPreview() {
    AvalonNavBarThemePreview(currentRoute = Screen.Settings.route) {
        SettingsContent(
            state = SettingsViewModel.UiState(
                serverUrl = "server.therealpercival.com",
                displayName = "Drew",
                accountName = "@drew654"
            )
        )
    }
}
