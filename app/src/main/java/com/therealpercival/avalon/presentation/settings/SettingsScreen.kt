package com.therealpercival.avalon.presentation.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.therealpercival.avalon.presentation.Screen
import com.therealpercival.avalon.presentation.ui.theme.AvalonNavBarThemePreview
import com.therealpercival.avalon.presentation.ui.theme.DayNightDevicePreviews

@Composable
fun SettingsScreen() {
    SettingsContent()
}

@Composable
private fun SettingsContent() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Settings Screen",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@DayNightDevicePreviews
@Composable
private fun SettingsScreenPreview() {
    AvalonNavBarThemePreview(currentRoute = Screen.Settings.route) {
        SettingsContent()
    }
}
