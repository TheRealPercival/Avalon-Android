package com.therealpercival.avalon.presentation.stats

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.therealpercival.avalon.presentation.ui.theme.DayNightDevicePreviews
import com.therealpercival.avalon.presentation.ui.theme.DeviceThemePreview

@Composable
fun StatsScreen() {
    StatsContent()
}

@Composable
private fun StatsContent() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Stats Screen",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@DayNightDevicePreviews
@Composable
private fun StatsScreenPreview() {
    DeviceThemePreview {
        StatsContent()
    }
}
