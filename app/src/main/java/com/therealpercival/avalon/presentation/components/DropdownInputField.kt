package com.therealpercival.avalon.presentation.components

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import com.therealpercival.avalon.R
import com.therealpercival.avalon.presentation.ui.conditional
import com.therealpercival.avalon.presentation.ui.theme.DayNightPreviews
import com.therealpercival.avalon.presentation.ui.theme.ThemePreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownInputField(
    value: String,
    onValueChange: (String) -> Unit,
    options: List<String>,
    isExpanded: Boolean = false,
    onExpandedChange: (Boolean) -> Unit = { },
    label: String? = null
) {
    val focusManager = LocalFocusManager.current

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = onExpandedChange
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            readOnly = true,
            label = {
                label?.let { Text(text = it) }
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
                    contentDescription = "Dropdown icon",
                    modifier = Modifier.conditional(isExpanded) {
                        rotate(degrees = 180f)
                    }
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable, true)
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                onExpandedChange(false)
                focusManager.clearFocus()
            }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        onValueChange(option)
                        onExpandedChange(false)
                        focusManager.clearFocus()
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

@DayNightPreviews
@Composable
private fun DropdownInputFieldPreview() {
    ThemePreview {
        DropdownInputField(
            value = "Test",
            onValueChange = { },
            options = listOf("Test", "Test 2", "Test 3")
        )
    }
}

@DayNightPreviews
@Composable
private fun DropdownInputFieldExpandedPreview() {
    ThemePreview {
        DropdownInputField(
            value = "Test",
            onValueChange = { },
            options = listOf("Test", "Test 2", "Test 3"),
            isExpanded = true,
            label = "Test label"
        )
    }
}
