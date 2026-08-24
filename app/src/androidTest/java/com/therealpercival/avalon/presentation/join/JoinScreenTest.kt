package com.therealpercival.avalon.presentation.join

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.therealpercival.avalon.R
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class JoinScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun joinScreen_displaysCorrectContents() {
        composeTestRule.setContent {
            JoinContent(
                state = JoinViewModel.UiState(
                    joinText = "Join game (5 in lobby)",
                    imageModels = listOf(R.drawable.x, R.drawable.benjson, R.drawable._shoe_)
                )
            )
        }

        composeTestRule.onNodeWithContentDescription("Avalon logo").assertIsDisplayed()
        composeTestRule.onNodeWithText("Avalon").assertIsDisplayed()
        composeTestRule.onNodeWithText("The Real Percival").assertIsDisplayed()
        composeTestRule.onNodeWithText("Join game (5 in lobby)").assertIsDisplayed()
    }

    @Test
    fun clickJoinGame_triggersCallback() {
        var clicked = false
        composeTestRule.setContent {
            JoinContent(
                state = JoinViewModel.UiState(
                    joinText = "Join game (5 in lobby)",
                    imageModels = listOf(R.drawable.x, R.drawable.benjson, R.drawable._shoe_)
                ),
                onJoinClicked = { clicked = true }
            )
        }

        composeTestRule.onNodeWithText("Join game (5 in lobby)").performClick()

        assertTrue(clicked)
    }
}
