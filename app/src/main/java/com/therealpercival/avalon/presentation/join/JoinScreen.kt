package com.therealpercival.avalon.presentation.join

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.therealpercival.avalon.R
import com.therealpercival.avalon.presentation.Screen
import com.therealpercival.avalon.presentation.components.AvalonLogo
import com.therealpercival.avalon.presentation.join.components.JoinButton
import com.therealpercival.avalon.presentation.ui.theme.AvalonNavBarThemePreview
import com.therealpercival.avalon.presentation.ui.theme.DayNightDevicePreviews

@Composable
fun JoinScreen(
    viewModel: JoinViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    JoinContent(
        state = state
    )
}

@Composable
private fun JoinContent(
    state: JoinViewModel.UiState,
    onJoinClicked: () -> Unit = { },
    placeholder: Painter = ColorPainter(Color.LightGray)
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AvalonLogo(
            modifier = Modifier.align(Alignment.TopCenter)
        )

        JoinButton(
            text = state.joinText,
            imageModels = state.imageModels,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
            onClick = onJoinClicked,
            placeholder = placeholder
        )
    }
}

@DayNightDevicePreviews
@Composable
private fun JoinScreenPreview1() {
    AvalonNavBarThemePreview(currentRoute = Screen.Join.route) {
        JoinContent(
            state = JoinViewModel.UiState(
                joinText = "Join game (5 in lobby)",
                imageModels = listOf(R.drawable.x, R.drawable.benjson, R.drawable._shoe_)
            )
        )
    }
}

@DayNightDevicePreviews
@Composable
private fun JoinScreenPreview2() {
    AvalonNavBarThemePreview(currentRoute = Screen.Join.route) {
        JoinContent(
            state = JoinViewModel.UiState(
                joinText = "Spectate (5 in game)",
                imageModels = listOf(R.drawable.x, R.drawable.benjson, R.drawable._shoe_)
            )
        )
    }
}

@DayNightDevicePreviews
@Composable
private fun JoinScreenPreview3() {
    AvalonNavBarThemePreview(currentRoute = Screen.Join.route) {
        JoinContent(
            state = JoinViewModel.UiState(
                joinText = "Spectate (lobby full)",
                imageModels = listOf(R.drawable.x, R.drawable.benjson, R.drawable._shoe_)
            )
        )
    }
}
