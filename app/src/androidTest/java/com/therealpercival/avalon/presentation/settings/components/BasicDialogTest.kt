package com.therealpercival.avalon.presentation.settings.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class BasicDialogTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun dialog_displaysTitle() {
        composeTestRule.setContent {
            BasicDialog(
                title = "Are you sure?",
                body = "Changing your current server will also sign you out.",
                confirmText = "Yes",
                dismissText = "Cancel"
            )
        }

        composeTestRule.onNodeWithText("Are you sure?").assertIsDisplayed()
    }

    @Test
    fun dialog_displaysBody() {
        composeTestRule.setContent {
            BasicDialog(
                title = "Are you sure?",
                body = "Changing your current server will also sign you out.",
                confirmText = "Yes",
                dismissText = "Cancel"
            )
        }

        composeTestRule.onNodeWithText("Changing your current server will also sign you out.").assertIsDisplayed()
    }

    @Test
    fun clickConfirm_triggersCallback() {
        var clicked = false
        composeTestRule.setContent {
            BasicDialog(
                title = "Are you sure?",
                body = "Changing your current server will also sign you out.",
                confirmText = "Yes",
                dismissText = "Cancel",
                onConfirm = { clicked = true }
            )
        }

        composeTestRule.onNodeWithText("Yes").performClick()

        assertTrue(clicked)
    }

    @Test
    fun clickDismiss_triggersCallback() {
        var clicked = false
        composeTestRule.setContent {
            BasicDialog(
                title = "Are you sure?",
                body = "Changing your current server will also sign you out.",
                confirmText = "Yes",
                dismissText = "Cancel",
                onDismiss = { clicked = true }
            )
        }

        composeTestRule.onNodeWithText("Cancel").performClick()

        assertTrue(clicked)
    }
}
