package com.therealpercival.avalon.presentation.ui.theme

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.therealpercival.avalon.presentation.components.AvalonBottomNavigation

@Preview(
    name = "Light Mode",
    group = "Themes",
    showBackground = true
)
@Preview(
    name = "Dark Mode",
    group = "Themes",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
annotation class DayNightPreviews

@Preview(
    name = "Light Mode",
    group = "Themes",
    showSystemUi = true,
    showBackground = true
)
@Preview(
    name = "Dark Mode",
    group = "Themes",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true,
    showBackground = true
)
annotation class DayNightDevicePreviews

@Preview(
    name = "Light Mode",
    group = "Themes",
    showSystemUi = true,
    showBackground = true,
    device = Devices.TABLET
)
@Preview(
    name = "Dark Mode",
    group = "Themes",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true,
    showBackground = true,
    device = Devices.TABLET
)
annotation class DayNightTabletPreviews

@Composable
fun ThemePreview(content: @Composable () -> Unit) {
    AvalonTheme {
        Surface {
            content()
        }
    }
}

@Composable
fun DeviceThemePreview(content: @Composable () -> Unit) {
    AvalonTheme {
        Scaffold { paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues)
            ) {
                content()
            }
        }
    }
}

@Composable
fun AvalonNavBarThemePreview(
    currentRoute: String,
    content: @Composable () -> Unit
) {
    AvalonTheme {
        Scaffold(
            bottomBar = {
                AvalonBottomNavigation(
                    currentRoute = currentRoute
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues)
            ) {
                content()
            }
        }
    }
}
