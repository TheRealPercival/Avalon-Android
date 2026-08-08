package com.therealpercival.avalon.presentation.settings.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class AssignNicknameDialogTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun assignNicknameDialog_displaysCorrectTitle() {
        val accountName = "@drew654"
        composeTestRule.setContent {
            AssignNicknameDialog(accountName = accountName)
        }

        composeTestRule.onNode(hasText("Nickname") and !hasSetTextAction()).assertIsDisplayed()
    }

    @Test
    fun assignNicknameDialog_displaysCorrectBody() {
        val accountName = "@drew654"
        composeTestRule.setContent {
            AssignNicknameDialog(accountName = accountName)
        }

        composeTestRule.onNodeWithText("Assign $accountName a nickname:").assertIsDisplayed()
    }

    @Test
    fun inputText_displaysCorrectPlaceholder() {
        val accountName = "@drew654"
        composeTestRule.setContent {
            AssignNicknameDialog(accountName = accountName)
        }

        composeTestRule.onNode(hasSetTextAction()).assertTextContains("Nickname")
    }

    @Test
    fun inputText_triggersCallback() {
        val accountName = "@drew654"
        var text = ""
        composeTestRule.setContent {
            AssignNicknameDialog(
                accountName = accountName,
                onNicknameChange = { text = it }
            )
        }

        composeTestRule.onNode(hasSetTextAction()).performClick()
        composeTestRule.onNode(hasSetTextAction()).performTextInput("Drew")

        assertEquals("Drew", text)
    }

    @Test
    fun clickAccept_triggersCallback() {
        val accountName = "@drew654"
        var clicked = false
        composeTestRule.setContent {
            AssignNicknameDialog(
                accountName = accountName,
                onConfirm = { clicked = true },
                nickname = "Drew"
            )
        }

        composeTestRule.onNodeWithText("Accept").performClick()

        assertTrue(clicked)
    }

    @Test
    fun clickAccept_disabled_whenNoText() {
        val accountName = "@drew654"
        composeTestRule.setContent {
            AssignNicknameDialog(
                accountName = accountName
            )
        }

        composeTestRule.onNodeWithText("Accept").assertIsDisplayed()
        composeTestRule.onNodeWithText("Accept").assertIsNotEnabled()
    }

    @Test
    fun clickCancel_triggersCallback() {
        val accountName = "@drew654"
        var clicked = false
        composeTestRule.setContent {
            AssignNicknameDialog(
                accountName = accountName,
                onDismiss = { clicked = true }
            )
        }

        composeTestRule.onNodeWithText("Cancel").performClick()

        assertTrue(clicked)
    }
}
