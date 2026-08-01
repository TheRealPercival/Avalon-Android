package com.therealpercival.avalon.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.therealpercival.avalon.R
import com.therealpercival.avalon.presentation.ui.theme.DayNightPreviews
import com.therealpercival.avalon.presentation.ui.theme.ThemePreview

@Composable
fun AvalonLogo(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = spacedBy(12.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Image(
            painter = painterResource(id = R.drawable.merlin_logo),
            contentDescription = "Avalon logo",
            modifier = Modifier
                .height(84.dp)
                .align(Alignment.Bottom)
                .padding(bottom = 6.dp)
        )
        Column(
            verticalArrangement = spacedBy((-10).dp)
        ) {
            Text(
                text = "Avalon",
                fontSize = 50.sp
            )
            Text(
                text = "The Real Percival",
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@DayNightPreviews
@Composable
private fun AvalonLogoPreview() {
    ThemePreview {
        AvalonLogo()
    }
}
