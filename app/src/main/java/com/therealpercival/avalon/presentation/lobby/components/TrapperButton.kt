package com.therealpercival.avalon.presentation.lobby.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.therealpercival.avalon.R
import com.therealpercival.avalon.presentation.ui.theme.DayNightPreviews
import com.therealpercival.avalon.presentation.ui.theme.ThemePreview

@Composable
fun TrapperButton(
    isSelected: Boolean = false
) {
    val backgroundColor =
        if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
    val tintColor =
        if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant

    Box(
        modifier = Modifier
            .background(color = backgroundColor, shape = MaterialTheme.shapes.medium)
            .size(64.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.outline_lock_24),
            contentDescription = "Trapper rule",
            tint = tintColor,
            modifier = Modifier.size(32.dp)
        )
    }
}

@DayNightPreviews
@Composable
private fun TrapperButtonPreview() {
    ThemePreview {
        TrapperButton()
    }
}

@DayNightPreviews
@Composable
private fun TrapperButtonSelectedPreview() {
    ThemePreview {
        TrapperButton(
            isSelected = true
        )
    }
}
