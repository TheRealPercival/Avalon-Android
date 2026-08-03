package com.therealpercival.avalon.presentation.settings.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.therealpercival.avalon.R
import com.therealpercival.avalon.presentation.settings.SettingsViewModel
import org.jetbrains.annotations.TestOnly
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class AdminSectionTest {
    @get:Rule
    val composeTestRule = createComposeRule()

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
    fun adminSection_displaysAcceptedText_whenAllowedProfilesIsNotEmpty() {
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
}
