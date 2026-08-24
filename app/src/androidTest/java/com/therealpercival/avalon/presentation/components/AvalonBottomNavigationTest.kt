package com.therealpercival.avalon.presentation.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.therealpercival.avalon.presentation.Screen
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class AvalonBottomNavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun avalonBottomNavigation_displaysAllItems() {
        composeTestRule.setContent {
            AvalonBottomNavigation(
                currentRoute = Screen.Join.route
            )
        }

        composeTestRule.onNodeWithText("Settings").assertIsDisplayed()
        composeTestRule.onNodeWithText("Game").assertIsDisplayed()
    }

    @Test
    fun clickSettings_triggersCallback() {
        var clickedScreen: Screen? = null
        composeTestRule.setContent {
            AvalonBottomNavigation(
                currentRoute = Screen.Join.route,
                onNavBarItemClicked = { clickedScreen = it }
            )
        }

        composeTestRule.onNodeWithText("Settings").performClick()

        assertEquals(Screen.Settings, clickedScreen)
    }

    @Test
    fun clickGame_triggersCallback() {
        var clickedScreen: Screen? = null
        composeTestRule.setContent {
            AvalonBottomNavigation(
                currentRoute = Screen.Join.route,
                onNavBarItemClicked = { clickedScreen = it }
            )
        }

        composeTestRule.onNodeWithText("Game").performClick()

        assertEquals(Screen.Join, clickedScreen)
    }
}
