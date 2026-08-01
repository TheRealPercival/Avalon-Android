package com.therealpercival.avalon.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.therealpercival.avalon.presentation.Screen
import com.therealpercival.avalon.presentation.ui.theme.DayNightPreviews
import com.therealpercival.avalon.presentation.ui.theme.ThemePreview

@Composable
fun AvalonBottomNavigation(
    currentRoute: String?,
    onNavBarItemClicked: (Screen) -> Unit = { }
) {
    val items = listOf(
        Screen.Settings,
        Screen.Join,
        Screen.Stats
    )

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    screen.icon?.let {
                        Icon(
                            painter = painterResource(id = it),
                            contentDescription = screen.title
                        )
                    }
                },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = { onNavBarItemClicked(screen) }
            )
        }
    }
}

@DayNightPreviews
@Composable
private fun AvalonBottomNavigationPreview() {
    ThemePreview {
        AvalonBottomNavigation(
            currentRoute = Screen.Join.route
        )
    }
}
