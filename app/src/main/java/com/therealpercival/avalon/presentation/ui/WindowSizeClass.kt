package com.therealpercival.avalon.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class WindowWidthSizeClass {
    Compact,
    Medium,
    Expanded,
}

enum class WindowHeightSizeClass {
    Compact,
    Medium,
    Expanded,
}

data class WindowSizeClass(
    val widthSizeClass: WindowWidthSizeClass,
    val heightSizeClass: WindowHeightSizeClass,
) {
    companion object {
        fun calculateFromSize(
            widthDp: Dp,
            heightDp: Dp,
        ): WindowSizeClass {
            val widthSizeClass = when {
                widthDp < 600.dp -> WindowWidthSizeClass.Compact
                widthDp < 840.dp -> WindowWidthSizeClass.Medium
                else -> WindowWidthSizeClass.Expanded
            }
            val heightSizeClass = when {
                heightDp < 480.dp -> WindowHeightSizeClass.Compact
                heightDp < 900.dp -> WindowHeightSizeClass.Medium
                else -> WindowHeightSizeClass.Expanded
            }
            return WindowSizeClass(widthSizeClass, heightSizeClass)
        }
    }
}

@Composable
fun rememberWindowSizeClass(): WindowSizeClass {
    val density = LocalDensity.current
    val windowInfo = LocalWindowInfo.current
    val widthDp = with(density) { windowInfo.containerSize.width.toDp() }
    val heightDp = with(density) { windowInfo.containerSize.height.toDp() }
    return WindowSizeClass.calculateFromSize(
        widthDp = widthDp,
        heightDp = heightDp,
    )
}
