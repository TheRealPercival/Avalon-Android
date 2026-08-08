package com.therealpercival.avalon.presentation.settings.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnySibling
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.therealpercival.avalon.R
import com.therealpercival.avalon.presentation.settings.SettingsViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class AdminSectionTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    // Requests ------------------------------------------------------------------------------------

    @Test
    fun adminSection_displaysRequestsText_whenRequestingProfilesIsNotEmpty() {
        composeTestRule.setContent {
            AdminSection(
                requestingProfiles = listOf(
                    SettingsViewModel.RequestingProfile(
                        accountName = "@ben.json",
                        avatarModel = R.drawable.benjson
                    ),
                    SettingsViewModel.RequestingProfile(
                        accountName = "@_shoe_",
                        avatarModel = R.drawable._shoe_
                    )
                ),
                allowedProfiles = listOf(
                    SettingsViewModel.AllowedProfile(
                        displayName = "Drew",
                        accountName = "@drew654",
                        avatarModel = R.drawable.x
                    )
                )
            )
        }

        composeTestRule.onNodeWithText("Requests").assertIsDisplayed()
    }

    @Test
    fun adminSection_doesNotDisplayRequestsText_whenRequestingProfilesIsEmpty() {
        composeTestRule.setContent {
            AdminSection(
                allowedProfiles = listOf(
                    SettingsViewModel.AllowedProfile(
                        displayName = "Drew",
                        accountName = "@drew654",
                        avatarModel = R.drawable.x
                    )
                )
            )
        }

        composeTestRule.onNodeWithText("Requests").assertDoesNotExist()
    }

    @Test
    fun adminSection_showsAllRequestingProfiles() {
        composeTestRule.setContent {
            AdminSection(
                requestingProfiles = listOf(
                    SettingsViewModel.RequestingProfile(
                        accountName = "@ben.json",
                        avatarModel = R.drawable.benjson
                    ),
                    SettingsViewModel.RequestingProfile(
                        accountName = "@_shoe_",
                        avatarModel = R.drawable._shoe_
                    )
                ),
                allowedProfiles = listOf(
                    SettingsViewModel.AllowedProfile(
                        displayName = "Drew",
                        accountName = "@drew654",
                        avatarModel = R.drawable.x
                    )
                )
            )
        }

        composeTestRule.onNodeWithText("@ben.json").assertIsDisplayed()
        composeTestRule.onNodeWithText("@_shoe_").assertIsDisplayed()
    }

    @Test
    fun clickAllow_triggersCallback() {
        var clickedProfile: SettingsViewModel.RequestingProfile? = null
        val targetProfile = SettingsViewModel.RequestingProfile(
            accountName = "@ben.json",
            avatarModel = R.drawable.benjson
        )
        composeTestRule.setContent {
            AdminSection(
                requestingProfiles = listOf(
                    targetProfile,
                    SettingsViewModel.RequestingProfile(
                        accountName = "@_shoe_",
                        avatarModel = R.drawable._shoe_
                    )
                ),
                onAllowClicked = { clickedProfile = it }
            )
        }

        composeTestRule.onNode(
            hasContentDescription("Allow") and hasAnySibling(hasText("@ben.json"))
        ).performClick()

        assertEquals(targetProfile, clickedProfile)
    }

    @Test
    fun clickDeny_triggersCallback() {
        var clickedProfile: SettingsViewModel.RequestingProfile? = null
        val targetProfile = SettingsViewModel.RequestingProfile(
            accountName = "@_shoe_",
            avatarModel = R.drawable._shoe_
        )
        composeTestRule.setContent {
            AdminSection(
                requestingProfiles = listOf(
                    SettingsViewModel.RequestingProfile(
                        accountName = "@ben.json",
                        avatarModel = R.drawable.benjson
                    ),
                    targetProfile
                ),
                onDenyClicked = { clickedProfile = it }
            )
        }

        composeTestRule.onNode(
            hasContentDescription("Deny") and hasAnySibling(hasText("@_shoe_"))
        ).performClick()

        assertEquals(targetProfile, clickedProfile)
    }

    // Accepted ------------------------------------------------------------------------------------

    @Test
    fun adminSection_displaysAcceptedText_whenAllowedProfilesIsNotEmpty() {
        composeTestRule.setContent {
            AdminSection(
                allowedProfiles = listOf(
                    SettingsViewModel.AllowedProfile(
                        displayName = "Drew",
                        accountName = "@drew654",
                        avatarModel = R.drawable.x
                    )
                )
            )
        }

        composeTestRule.onNodeWithText("Accepted").assertIsDisplayed()
    }

    @Test
    fun adminSection_doesNotDisplayAcceptedText_whenAllowedProfilesIsEmpty() {
        composeTestRule.setContent {
            AdminSection(
                requestingProfiles = listOf(
                    SettingsViewModel.RequestingProfile(
                        accountName = "@ben.json",
                        avatarModel = R.drawable.benjson
                    ),
                    SettingsViewModel.RequestingProfile(
                        accountName = "@_shoe_",
                        avatarModel = R.drawable._shoe_
                    )
                )
            )
        }

        composeTestRule.onNodeWithText("Accepted").assertDoesNotExist()
    }

    @Test
    fun adminSection_showsAllAllowedProfiles() {
        composeTestRule.setContent {
            AdminSection(
                requestingProfiles = listOf(
                    SettingsViewModel.RequestingProfile(
                        accountName = "@_shoe_",
                        avatarModel = R.drawable._shoe_
                    )
                ),
                allowedProfiles = listOf(
                    SettingsViewModel.AllowedProfile(
                        displayName = "Ben",
                        accountName = "@ben.json",
                        avatarModel = R.drawable.benjson
                    ),
                    SettingsViewModel.AllowedProfile(
                        displayName = "Drew",
                        accountName = "@drew654",
                        avatarModel = R.drawable.x
                    )
                )
            )
        }

        composeTestRule.onNodeWithText("Ben").assertIsDisplayed()
        composeTestRule.onNodeWithText("@ben.json").assertIsDisplayed()

        composeTestRule.onNodeWithText("Drew").assertIsDisplayed()
        composeTestRule.onNodeWithText("@drew654").assertIsDisplayed()
    }

    @Test
    fun clickRemove_triggersCallback() {
        var clickedProfile: SettingsViewModel.AllowedProfile? = null
        val targetProfile = SettingsViewModel.AllowedProfile(
            displayName = "Ben",
            accountName = "@ben.json",
            avatarModel = R.drawable.benjson
        )
        composeTestRule.setContent {
            AdminSection(
                allowedProfiles = listOf(
                    SettingsViewModel.AllowedProfile(
                        displayName = "Drew",
                        accountName = "@drew654",
                        avatarModel = R.drawable.x
                    ),
                    targetProfile
                ),
                onRemoveClicked = { clickedProfile = it }
            )
        }

        composeTestRule.onNode(
            hasContentDescription("Remove") and hasAnySibling(hasText("Ben"))
        ).performClick()

        assertEquals(targetProfile, clickedProfile)
    }
}
