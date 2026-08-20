package com.therealpercival.avalon.presentation.components

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class DropdownInputFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun dropdownInputField_displaysCorrectContents() {
        composeTestRule.setContent {
            DropdownInputField(
                value = "Test",
                onValueChange = { },
                options = listOf("Test", "Test 2", "Test 3")
            )
        }

        composeTestRule.onNodeWithText("Test").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Dropdown icon").assertIsDisplayed()
    }

    @Test
    fun dropdownInputField_displaysLabelCorrectly() {
        composeTestRule.setContent {
            DropdownInputField(
                value = "Test",
                onValueChange = { },
                options = listOf("Test", "Test 2", "Test 3"),
                label = "Test label"
            )
        }

        composeTestRule.onNodeWithText("Test label").assertIsDisplayed()
    }

    @Test
    fun dropdownInputField_displaysExpandedCorrectly() {
        composeTestRule.setContent {
            DropdownInputField(
                value = "Test",
                onValueChange = { },
                options = listOf("Test", "Test 2", "Test 3"),
                isExpanded = true
            )
        }

        composeTestRule.onNodeWithContentDescription("Dropdown icon").assertIsDisplayed()
        composeTestRule.onAllNodesWithText("Test").assertCountEquals(2)
        composeTestRule.onNodeWithText("Test 2").assertIsDisplayed()
        composeTestRule.onNodeWithText("Test 3").assertIsDisplayed()
    }

    @Test
    fun clickInputField_expandsCorrectly() {
        val isExpanded = mutableStateOf(false)
        composeTestRule.setContent {
            DropdownInputField(
                value = "Test",
                onValueChange = { },
                options = listOf("Test", "Test 2", "Test 3"),
                isExpanded = isExpanded.value,
                onExpandedChange = { isExpanded.value = it }
            )
        }

        composeTestRule.onNodeWithText("Test").performClick()

        composeTestRule.onNodeWithText("Test 2").assertIsDisplayed()
    }

    @Test
    fun clickInputFieldOption_triggersCallback() {
        var capturedValue: String? = null
        composeTestRule.setContent {
            DropdownInputField(
                value = "Test",
                onValueChange = { capturedValue = it },
                options = listOf("Test", "Test 2", "Test 3"),
                isExpanded = true
            )
        }

        composeTestRule.onNodeWithText("Test 2").performClick()

        assertEquals("Test 2", capturedValue)
    }
}
