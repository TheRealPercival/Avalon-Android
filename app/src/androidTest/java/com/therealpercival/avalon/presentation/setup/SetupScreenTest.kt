package com.therealpercival.avalon.presentation.setup

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class SetupScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun setupScreen_displaysCorrectComponents_whenUnvalidated() {
        composeTestRule.setContent {
            SetupContent(
                state = SetupViewModel.UiState()
            )
        }

        composeTestRule.onNodeWithText("Avalon").assertIsDisplayed()
        composeTestRule.onNodeWithText("The Real Percival").assertIsDisplayed()

        composeTestRule.onNodeWithText("You've arrived in Avalon!").assertIsDisplayed()
        composeTestRule.onNodeWithText("Welcome to an online adaptation of Don Eskridge's Avalon: Big Box Edition. Please enter your group's server URL below to begin.").assertIsDisplayed()

        composeTestRule.onNodeWithText("Server URL").assertIsDisplayed()
        composeTestRule.onNodeWithText("Connect").assertIsDisplayed()
    }

    @Test
    fun setupScreen_displaysCorrectComponents_whenFetching() {
        composeTestRule.setContent {
            SetupContent(
                state = SetupViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    serverUrlState = SetupViewModel.ServerUrlState.Fetching
                )
            )
        }

        composeTestRule.onNodeWithText("Avalon").assertIsDisplayed()
        composeTestRule.onNodeWithText("The Real Percival").assertIsDisplayed()

        composeTestRule.onNodeWithText("You've arrived in Avalon!").assertIsDisplayed()
        composeTestRule.onNodeWithText("Welcome to an online adaptation of Don Eskridge's Avalon: Big Box Edition. Please enter your group's server URL below to begin.").assertIsDisplayed()

        composeTestRule.onNodeWithText("Server URL").assertIsDisplayed()
        composeTestRule.onNodeWithText("server.therealpercival.com").assertIsDisplayed()

        composeTestRule.onNodeWithText("Connect").assertIsNotDisplayed()
    }

    @Test
    fun componentsAreDisabled_whenFetching() {
        composeTestRule.setContent {
            SetupContent(
                state = SetupViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    serverUrlState = SetupViewModel.ServerUrlState.Fetching
                )
            )
        }

        composeTestRule.onNodeWithText("Server URL").assertIsNotEnabled()

        composeTestRule.onNodeWithText("Connect").assertIsNotDisplayed()
        composeTestRule.onNodeWithTag("SetupScreen_ConnectButton").assertIsNotEnabled()
    }

    @Test
    fun setupScreen_displaysCorrectComponents_whenValid() {
        composeTestRule.setContent {
            SetupContent(
                state = SetupViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    serverUrlState = SetupViewModel.ServerUrlState.Valid
                )
            )
        }

        composeTestRule.onNodeWithText("Avalon").assertIsDisplayed()
        composeTestRule.onNodeWithText("The Real Percival").assertIsDisplayed()

        composeTestRule.onNodeWithText("Identify yourself!").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sign in to your Discord account below so your game history can be saved.").assertIsDisplayed()

        composeTestRule.onNodeWithText("Sign in with Discord").assertIsDisplayed()
        composeTestRule.onNodeWithText("Connected to server.therealpercival.com").assertIsDisplayed()

        composeTestRule.onNodeWithTag("SetupScreen_ConnectButton").assertIsNotDisplayed()
    }
}
