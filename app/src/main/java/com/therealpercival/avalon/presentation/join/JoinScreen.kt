package com.therealpercival.avalon.presentation.join

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.lifecycle.viewmodel.compose.viewModel
import com.therealpercival.avalon.R
import com.therealpercival.avalon.presentation.join.components.JoinButton
import com.therealpercival.avalon.presentation.ui.theme.DayNightDevicePreviews
import com.therealpercival.avalon.presentation.ui.theme.DeviceThemePreview

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
fun JoinContent(
    state: JoinViewModel.UiState,
    onJoinClicked: () -> Unit = { },
    placeholder: Painter = ColorPainter(Color.LightGray)
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        JoinButton(
            text = state.joinText,
            imageModels = state.imageModels,
            onClick = onJoinClicked,
            placeholder = placeholder
        )
    }
}

@DayNightDevicePreviews
@Composable
fun JoinScreenPreview() {
    DeviceThemePreview {
        JoinContent(
            state = JoinViewModel.UiState(
                joinText = "Join game (5 in lobby)",
                imageModels = listOf(R.drawable.x, R.drawable.benjson, R.drawable._shoe_)
            )
        )
    }
}
