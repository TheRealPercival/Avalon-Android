package com.therealpercival.avalon.presentation.settings.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.therealpercival.avalon.R
import com.therealpercival.avalon.presentation.settings.SettingsViewModel
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class RequestingProfileListItemTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val profile = SettingsViewModel.RequestingProfile(
        accountName = "@drew654",
        avatarModel = R.drawable.x
    )

    @Test
    fun requestProfileListItem_displaysAccountName() {
        composeTestRule.setContent {
            RequestingProfileListItem(profile = profile)
        }

        composeTestRule.onNodeWithText("@drew654").assertIsDisplayed()
    }

    @Test
    fun requestProfileListItem_displaysAvatar() {
        composeTestRule.setContent {
            RequestingProfileListItem(profile = profile)
        }

        composeTestRule.onNodeWithContentDescription("Avatar").assertIsDisplayed()
    }

    @Test
    fun clickAllow_triggersCallback() {
        var clicked = false
        composeTestRule.setContent {
            RequestingProfileListItem(
                profile = profile,
                onAllowClicked = { clicked = true }
            )
        }

        composeTestRule.onNodeWithContentDescription("Allow").performClick()

        assertTrue(clicked)
    }

    @Test
    fun clickDeny_triggersCallback() {
        var clicked = false
        composeTestRule.setContent {
            RequestingProfileListItem(
                profile = profile,
                onDenyClicked = { clicked = true }
            )
        }

        composeTestRule.onNodeWithContentDescription("Deny").performClick()

        assertTrue(clicked)
    }
}
