package com.therealpercival.avalon.presentation

import com.therealpercival.avalon.R

sealed class Screen(val route: String, val title: String, val icon: Int? = null) {
    object Splash : Screen("splash", "Splash")
    object Setup : Screen("setup", "Setup")
    object Join : Screen("join", "Game", R.drawable.baseline_videogame_asset_24)
    object Settings : Screen("settings", "Settings", R.drawable.baseline_settings_24)
    object Stats : Screen("stats", "Stats", R.drawable.baseline_query_stats_24)
}
