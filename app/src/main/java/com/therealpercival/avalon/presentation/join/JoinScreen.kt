package com.therealpercival.avalon.presentation.join

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import com.therealpercival.avalon.R
import com.therealpercival.avalon.presentation.join.components.JoinButton
import com.therealpercival.avalon.presentation.ui.theme.DayNightDevicePreviews
import com.therealpercival.avalon.presentation.ui.theme.DeviceThemePreview

@Composable
fun JoinScreen() {

}

@Composable
fun JoinContent(
    joinText: String,
    imageModels: List<Any>,
    onJoinClicked: () -> Unit = { },
    placeholder: Painter = ColorPainter(Color.LightGray)
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        JoinButton(
            text = joinText,
            imageModels = imageModels,
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
            joinText = "Join game (5 in lobby)",
            imageModels = listOf(R.drawable.x, R.drawable.benjson, R.drawable._shoe_)
        )
    }
}
