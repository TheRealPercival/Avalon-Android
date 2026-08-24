package com.therealpercival.avalon.presentation.setup.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class ServerUrlSectionTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun serverUrlSection_displaysCorrectText() {
        composeTestRule.setContent {
            ServerUrlSection()
        }

        composeTestRule.onNodeWithText("You've arrived in Avalon!").assertIsDisplayed()
        composeTestRule.onNodeWithText("Welcome to an online adaptation of Don Eskridge's Avalon: Big Box Edition. Please enter your group's server URL below to begin.").assertIsDisplayed()
        composeTestRule.onNodeWithText("Server URL").assertIsDisplayed()
    }

    @Test
    fun typeServerUrl_triggersCallback() {
        var serverUrl = ""
        composeTestRule.setContent {
            ServerUrlSection(
                onServerUrlChange = { serverUrl = it }
            )
        }

        composeTestRule.onNodeWithText("Server URL").performTextInput("server.therealpercival.com")

        assertEquals("server.therealpercival.com", serverUrl)
    }

    @Test
    fun clickDone_triggersCallback() {
        var capturedUrl = ""
        var clicked = false
        composeTestRule.setContent {
            var text by remember { mutableStateOf("") }
            ServerUrlSection(
                serverUrl = text,
                onServerUrlChange = { 
                    text = it
                    capturedUrl = it
                },
                onDone = { clicked = true }
            )
        }

        val textField = composeTestRule.onNodeWithText("Server URL")
        textField.performTextInput("server.therealpercival.com")
        textField.performImeAction()

        assertEquals("server.therealpercival.com", capturedUrl)
        assertTrue(clicked)
    }

    @Test
    fun textField_isNotEnabled_ifNotEnabled() {
        composeTestRule.setContent {
            ServerUrlSection(
                serverUrl = "server.therealpercival.com",
                isInputEnabled = false
            )
        }

        composeTestRule.onNodeWithText("Server URL").assertIsNotEnabled()
    }

    @Test
    fun textField_hasError_whenErrorState() {
        composeTestRule.setContent {
            ServerUrlSection(
                serverUrl = "server.therealpercival.com",
                isError = true
            )
        }

        composeTestRule.onNodeWithText("Server URL").assert(hasText("Invalid server URL"))
    }
}
