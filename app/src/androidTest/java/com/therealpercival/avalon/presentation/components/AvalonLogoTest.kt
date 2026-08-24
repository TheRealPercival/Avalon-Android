package com.therealpercival.avalon.presentation.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class AvalonLogoTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun avalonLogo_displaysCorrectContents() {
        composeTestRule.setContent {
            AvalonLogo()
        }

        composeTestRule.onNodeWithContentDescription("Avalon logo").assertIsDisplayed()
        composeTestRule.onNodeWithText("Avalon").assertIsDisplayed()
        composeTestRule.onNodeWithText("The Real Percival").assertIsDisplayed()
    }
}
