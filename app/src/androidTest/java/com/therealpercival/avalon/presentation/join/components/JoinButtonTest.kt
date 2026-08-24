package com.therealpercival.avalon.presentation.join.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.therealpercival.avalon.R
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class JoinButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun joinButton_displaysCorrectText() {
        composeTestRule.setContent {
            JoinButton(
                text = "Join game (5 in lobby)",
                imageModels = listOf(
                    R.drawable.x,
                    R.drawable.benjson,
                    R.drawable._shoe_
                )
            )
        }

        composeTestRule.onNodeWithText("Join game (5 in lobby)").assertIsDisplayed()
    }

    @Test
    fun clickButton_triggersCallback() {
        var clicked = false
        composeTestRule.setContent {
            JoinButton(
                text = "Join game (5 in lobby)",
                imageModels = listOf(
                    R.drawable.x,
                    R.drawable.benjson,
                    R.drawable._shoe_
                ),
                onClick = { clicked = true }
            )
        }


        composeTestRule.onNodeWithText("Join game (5 in lobby)").performClick()

        assertTrue(clicked)
    }
}
