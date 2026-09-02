package com.therealpercival.avalon.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.therealpercival.avalon.presentation.components.AvalonBottomNavigation
import com.therealpercival.avalon.presentation.join.JoinScreen
import com.therealpercival.avalon.presentation.settings.SettingsScreen
import com.therealpercival.avalon.presentation.setup.SetupScreen
import com.therealpercival.avalon.presentation.splash.SplashScreen
import com.therealpercival.avalon.presentation.stats.StatsScreen

@Composable
fun AvalonNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val isShowingBottomNavBar = currentRoute in listOf(
        Screen.Join.route,
        Screen.Stats.route,
        Screen.Settings.route
    )

    Scaffold(
        bottomBar = {
            if (isShowingBottomNavBar) {
                AvalonBottomNavigation(
                    currentRoute = currentRoute,
                    onNavBarItemClicked = { screen ->
                        navController.navigate(screen.route) {
                            popUpTo(Screen.Join.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(
                    onNavigateToSetup = {
                        navController.navigate(Screen.Setup.route) {
                            popUpTo(Screen.Splash.route) {
                                inclusive = true
                            }
                        }
                    },
                    onNavigateToJoin = {
                        navController.navigate(Screen.Join.route) {
                            popUpTo(Screen.Splash.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable(Screen.Setup.route) {
                SetupScreen(
                    onSignInSuccess = {
                        navController.navigate(Screen.Join.route) {
                            popUpTo(Screen.Setup.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable(Screen.Join.route) {
                JoinScreen()
            }
            composable(Screen.Stats.route) {
                StatsScreen()
            }
            composable(Screen.Settings.route) {
                SettingsScreen(
                    onSignOutSuccess = {
                        navController.navigate(Screen.Setup.route) {
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
    }
}
