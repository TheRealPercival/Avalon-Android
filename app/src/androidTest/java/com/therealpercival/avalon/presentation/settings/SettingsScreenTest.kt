package com.therealpercival.avalon.presentation.settings

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test

class SettingsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun settingsScreen_displaysServerUrl() {
        composeTestRule.setContent {
            SettingsContent(
                state = SettingsViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    displayName = "Drew",
                    accountName = "@drew654"
                )
            )
        }

        composeTestRule.onNodeWithText("Server URL").assertIsDisplayed()
        composeTestRule.onNodeWithText("server.therealpercival.com").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Server URL is valid").assertIsDisplayed()
    }

    @Test
    fun settingsScreen_displaysDisplayName() {
        composeTestRule.setContent {
            SettingsContent(
                state = SettingsViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    displayName = "Drew",
                    accountName = "@drew654"
                )
            )
        }

        composeTestRule.onNodeWithText("Account").assertIsDisplayed()
        composeTestRule.onNodeWithText("Drew").assertIsDisplayed()
        composeTestRule.onNodeWithText("@drew654").assertIsDisplayed()
    }

    @Test
    fun clickChangeServer_triggersCallback() {
        var clicked = false
        composeTestRule.setContent {
            SettingsContent(
                state = SettingsViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    displayName = "Drew",
                    accountName = "@drew654"
                ),
                onChangeServerClicked = { clicked = true }
            )
        }

        composeTestRule.onNodeWithText("Change Server").performClick()

        assertTrue(clicked)
    }

    @Test
    fun clickSignOut_triggersCallback() {
        var clicked = false
        composeTestRule.setContent {
            SettingsContent(
                state = SettingsViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    displayName = "Drew",
                    accountName = "@drew654"
                ),
                onSignOutClicked = { clicked = true }
            )
        }

        composeTestRule.onNodeWithText("Sign Out").performClick()

        assertTrue(clicked)
    }
}
