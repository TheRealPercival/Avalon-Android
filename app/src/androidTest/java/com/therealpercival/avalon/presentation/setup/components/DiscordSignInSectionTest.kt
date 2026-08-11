package com.therealpercival.avalon.presentation.setup.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class DiscordSignInSectionTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun discordSignInSection_displaysCorrectText() {
        composeTestRule.setContent {
            DiscordSignInSection(
                serverUrl = "server.therealpercival.com"
            )
        }

        composeTestRule.onNodeWithText("Identify yourself!").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sign in to your Discord account below so your game history can be saved.").assertIsDisplayed()
        composeTestRule.onNodeWithText("Connected to server.therealpercival.com").assertIsDisplayed()
    }

    @Test
    fun clickSignIn_triggersCallback() {
        var clicked = false
        composeTestRule.setContent {
            DiscordSignInSection(
                serverUrl = "server.therealpercival.com",
                onSignInClicked = { clicked = true }
            )
        }

        composeTestRule.onNodeWithText("Sign in with Discord").performClick()

        assertTrue(clicked)
    }
}
