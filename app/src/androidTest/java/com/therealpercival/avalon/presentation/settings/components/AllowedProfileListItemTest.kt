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

class AllowedProfileListItemTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val profile = SettingsViewModel.AllowedProfile(
        displayName = "Drew",
        accountName = "@drew654",
        avatarModel = R.drawable.x
    )

    @Test
    fun allowedProfileListItem_displaysDisplayName() {
        composeTestRule.setContent {
            AllowedProfileListItem(profile = profile)
        }

        composeTestRule.onNodeWithText("Drew").assertIsDisplayed()
    }

    @Test
    fun allowedProfileListItem_displaysAccountName() {
        composeTestRule.setContent {
            AllowedProfileListItem(profile = profile)
        }

        composeTestRule.onNodeWithText("@drew654").assertIsDisplayed()
    }

    @Test
    fun allowedProfileListItem_displaysAvatar() {
        composeTestRule.setContent {
            AllowedProfileListItem(profile = profile)
        }

        composeTestRule.onNodeWithContentDescription("Avatar").assertIsDisplayed()
    }

    @Test
    fun clickRemove_triggersCallback() {
        var clicked = false
        composeTestRule.setContent {
            AllowedProfileListItem(
                profile = profile,
                onRemoveClicked = { clicked = true }
            )
        }

        composeTestRule.onNodeWithContentDescription("Remove").performClick()

        assertTrue(clicked)
    }
}
