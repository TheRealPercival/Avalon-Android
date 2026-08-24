package com.therealpercival.avalon.presentation.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.therealpercival.avalon.presentation.ui.theme.DayNightDevicePreviews
import com.therealpercival.avalon.presentation.ui.theme.DeviceThemePreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicDialog(
    title: String,
    body: String,
    confirmText: String,
    dismissText: String,
    onConfirm: () -> Unit = { },
    onDismiss: () -> Unit = { }
) {
    BasicAlertDialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation,
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = body
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = {
                            onDismiss()
                        },
                    ) {
                        Text(dismissText)
                    }
                    TextButton(
                        onClick = {
                            onConfirm()
                        },
                    ) {
                        Text(confirmText)
                    }
                }
            }
        }
    }
}

@DayNightDevicePreviews
@Composable
private fun BasicDialogPreview() {
    DeviceThemePreview {
        BasicDialog(
            title = "Are you sure?",
            body = "Changing your current server will also sign you out.",
            confirmText = "Yes",
            dismissText = "Cancel"
        )
    }
}
