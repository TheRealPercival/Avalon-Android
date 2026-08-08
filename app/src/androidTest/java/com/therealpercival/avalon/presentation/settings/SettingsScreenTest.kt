package com.therealpercival.avalon.presentation.settings

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnySibling
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.therealpercival.avalon.R
import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class SettingsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    // Base settings section -----------------------------------------------------------------------

    @Test
    fun settingsScreen_displaysServerUrl() {
        composeTestRule.setContent {
            SettingsContent(
                state = SettingsViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    displayName = "Drew",
                    accountName = "@drew654"
                )
            )
        }

        composeTestRule.onNodeWithText("Server URL").assertIsDisplayed()
        composeTestRule.onNodeWithText("server.therealpercival.com").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Server URL is valid").assertIsDisplayed()
    }

    @Test
    fun settingsScreen_displaysDisplayName() {
        composeTestRule.setContent {
            SettingsContent(
                state = SettingsViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    displayName = "Drew",
                    accountName = "@drew654"
                )
            )
        }

        composeTestRule.onNodeWithText("Account").assertIsDisplayed()
        composeTestRule.onNodeWithText("Drew").assertIsDisplayed()
        composeTestRule.onNodeWithText("@drew654").assertIsDisplayed()
    }

    @Test
    fun clickChangeServer_triggersCallback() {
        var clicked = false
        composeTestRule.setContent {
            SettingsContent(
                state = SettingsViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    displayName = "Drew",
                    accountName = "@drew654"
                ),
                onChangeServerClicked = { clicked = true }
            )
        }

        composeTestRule.onNodeWithText("Change Server").performClick()

        assertTrue(clicked)
    }

    @Test
    fun clickSignOut_triggersCallback() {
        var clicked = false
        composeTestRule.setContent {
            SettingsContent(
                state = SettingsViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    displayName = "Drew",
                    accountName = "@drew654"
                ),
                onSignOutClicked = { clicked = true }
            )
        }

        composeTestRule.onNodeWithText("Sign Out").performClick()

        assertTrue(clicked)
    }

    // Admin section - Requests --------------------------------------------------------------------

    @Test
    fun clickAllow_triggersCallback() {
        var clickedProfile: SettingsViewModel.RequestingProfile? = null
        val targetProfile = SettingsViewModel.RequestingProfile(
            accountName = "@ben.json",
            avatarModel = R.drawable.benjson
        )
        composeTestRule.setContent {
            SettingsContent(
                state = SettingsViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    displayName = "Drew",
                    accountName = "@drew654",
                    isAdmin = true,
                    requestingProfiles = listOf(targetProfile)
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
            accountName = "@ben.json",
            avatarModel = R.drawable.benjson
        )
        composeTestRule.setContent {
            SettingsContent(
                state = SettingsViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    displayName = "Drew",
                    accountName = "@drew654",
                    isAdmin = true,
                    requestingProfiles = listOf(targetProfile)
                ),
                onDenyClicked = { clickedProfile = it }
            )
        }

        composeTestRule.onNode(
            hasContentDescription("Deny") and hasAnySibling(hasText("@ben.json"))
        ).performClick()

        assertEquals(targetProfile, clickedProfile)
    }

    // Admin section - Accepted --------------------------------------------------------------------

    @Test
    fun clickRemove_triggersCallback() {
        var clickedProfile: SettingsViewModel.AllowedProfile? = null
        val targetProfile = SettingsViewModel.AllowedProfile(
            displayName = "Ben",
            accountName = "@ben.json",
            avatarModel = R.drawable.benjson
        )
        composeTestRule.setContent {
            SettingsContent(
                state = SettingsViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    displayName = "Drew",
                    accountName = "@drew654",
                    isAdmin = true,
                    allowedProfiles = listOf(targetProfile)
                ),
                onRemoveClicked = { clickedProfile = it }
            )
        }

        composeTestRule.onNode(
            hasContentDescription("Remove") and hasAnySibling(hasText("Ben"))
        ).performClick()

        assertEquals(targetProfile, clickedProfile)
    }

    // Admin section - Server dialog ---------------------------------------------------------------

    @Test
    fun clickServerDialogYes_triggersCallback() {
        var clicked = false
        composeTestRule.setContent {
            SettingsContent(
                state = SettingsViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    displayName = "Drew",
                    accountName = "@drew654",
                    dialogType = SettingsViewModel.DialogType.Server
                ),
                onConfirmChangeServerClicked = { clicked = true }
            )
        }

        composeTestRule.onNodeWithText("Yes").performClick()

        assertTrue(clicked)
    }

    @Test
    fun clickServerDialogCancel_triggersCallback() {
        var clicked = false
        composeTestRule.setContent {
            SettingsContent(
                state = SettingsViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    displayName = "Drew",
                    accountName = "@drew654",
                    dialogType = SettingsViewModel.DialogType.Server
                ),
                onDismissDialog = { clicked = true }
            )
        }

        composeTestRule.onNodeWithText("Cancel").performClick()

        assertTrue(clicked)
    }

    // Admin section - Sign out dialog -------------------------------------------------------------

    @Test
    fun clickSignOutDialogYes_triggersCallback() {
        var clicked = false
        composeTestRule.setContent {
            SettingsContent(
                state = SettingsViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    displayName = "Drew",
                    accountName = "@drew654",
                    dialogType = SettingsViewModel.DialogType.SignOut
                ),
                onConfirmSignOutClicked = { clicked = true }
            )
        }

        composeTestRule.onNodeWithText("Yes").performClick()

        assertTrue(clicked)
    }

    @Test
    fun clickSignOutDialogCancel_triggersCallback() {
        var clicked = false
        composeTestRule.setContent {
            SettingsContent(
                state = SettingsViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    displayName = "Drew",
                    accountName = "@drew654",
                    dialogType = SettingsViewModel.DialogType.SignOut
                ),
                onDismissDialog = { clicked = true }
            )
        }

        composeTestRule.onNodeWithText("Cancel").performClick()

        assertTrue(clicked)
    }

    // Admin section - Assign nickname dialog ------------------------------------------------------

    @Test
    fun assignNicknameDialog_displaysAccountName() {
        composeTestRule.setContent {
            SettingsContent(
                state = SettingsViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    displayName = "Drew",
                    accountName = "@drew654",
                    isAdmin = true,
                    requestingProfiles = listOf(
                        SettingsViewModel.RequestingProfile(
                            accountName = "@ben.json",
                            avatarModel = R.drawable.benjson
                        )
                    ),
                    dialogType = SettingsViewModel.DialogType.AssignNickname,
                    selectedRequestingProfile = SettingsViewModel.RequestingProfile(
                        accountName = "@ben.json",
                        avatarModel = R.drawable.benjson
                    )
                )
            )
        }

        composeTestRule.onNodeWithText("@ben.json").assertIsDisplayed()
    }

    @Test
    fun assignNicknameDialog_displaysNickname() {
        composeTestRule.setContent {
            SettingsContent(
                state = SettingsViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    displayName = "Drew",
                    accountName = "@drew654",
                    isAdmin = true,
                    requestingProfiles = listOf(
                        SettingsViewModel.RequestingProfile(
                            accountName = "@ben.json",
                            avatarModel = R.drawable.benjson
                        )
                    ),
                    dialogType = SettingsViewModel.DialogType.AssignNickname,
                    selectedRequestingProfile = SettingsViewModel.RequestingProfile(
                        accountName = "@ben.json",
                        avatarModel = R.drawable.benjson
                    ),
                    nickname = "Ben"
                )
            )
        }

        composeTestRule.onNodeWithText("Ben").assertIsDisplayed()
    }

    @Test
    fun clickAssignNicknameDialogAccept_triggersCallback() {
        var clicked = false
        composeTestRule.setContent {
            SettingsContent(
                state = SettingsViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    displayName = "Drew",
                    accountName = "@drew654",
                    isAdmin = true,
                    requestingProfiles = listOf(
                        SettingsViewModel.RequestingProfile(
                            accountName = "@ben.json",
                            avatarModel = R.drawable.benjson
                        )
                    ),
                    dialogType = SettingsViewModel.DialogType.AssignNickname,
                    selectedRequestingProfile = SettingsViewModel.RequestingProfile(
                        accountName = "@ben.json",
                        avatarModel = R.drawable.benjson
                    ),
                    nickname = "Ben"
                ),
                onConfirmAllowClicked = { clicked = true }
            )
        }

        composeTestRule.onNodeWithText("Accept").performClick()

        assertTrue(clicked)
    }

    @Test
    fun clickAssignNicknameDialogCancel_triggersCallback() {
        var clicked = false
        composeTestRule.setContent {
            SettingsContent(
                state = SettingsViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    displayName = "Drew",
                    accountName = "@drew654",
                    isAdmin = true,
                    requestingProfiles = listOf(
                        SettingsViewModel.RequestingProfile(
                            accountName = "@ben.json",
                            avatarModel = R.drawable.benjson
                        )
                    ),
                    dialogType = SettingsViewModel.DialogType.AssignNickname,
                    selectedRequestingProfile = SettingsViewModel.RequestingProfile(
                        accountName = "@ben.json",
                        avatarModel = R.drawable.benjson
                    ),
                    nickname = "Ben"
                ),
                onDismissDialog = { clicked = true }
            )
        }

        composeTestRule.onNodeWithText("Cancel").performClick()

        assertTrue(clicked)
    }

    // Admin section - Deny profile dialog ---------------------------------------------------------

    @Test
    fun denyProfileDialog_displaysAccountName() {
        val targetProfile = SettingsViewModel.RequestingProfile(
            accountName = "@ben.json",
            avatarModel = R.drawable.benjson
        )
        composeTestRule.setContent {
            SettingsContent(
                state = SettingsViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    displayName = "Drew",
                    accountName = "@drew654",
                    isAdmin = true,
                    requestingProfiles = listOf(targetProfile),
                    dialogType = SettingsViewModel.DialogType.DenyProfile,
                    selectedRequestingProfile = targetProfile
                )
            )
        }

        composeTestRule.onNodeWithText("@ben.json").assertIsDisplayed()
    }

    @Test
    fun clickDenyProfileDialogReject_triggersCallback() {
        var clicked = false
        val targetProfile = SettingsViewModel.RequestingProfile(
            accountName = "@ben.json",
            avatarModel = R.drawable.benjson
        )
        composeTestRule.setContent {
            SettingsContent(
                state = SettingsViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    displayName = "Drew",
                    accountName = "@drew654",
                    isAdmin = true,
                    requestingProfiles = listOf(targetProfile),
                    dialogType = SettingsViewModel.DialogType.DenyProfile,
                    selectedRequestingProfile = targetProfile
                ),
                onConfirmDenyProfileClicked = { clicked = true }
            )
        }

        composeTestRule.onNodeWithText("Reject").performClick()

        assertTrue(clicked)
    }

    @Test
    fun clickDenyProfileDialogCancel_triggersCallback() {
        var clicked = false
        val targetProfile = SettingsViewModel.RequestingProfile(
            accountName = "@ben.json",
            avatarModel = R.drawable.benjson
        )
        composeTestRule.setContent {
            SettingsContent(
                state = SettingsViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    displayName = "Drew",
                    accountName = "@drew654",
                    isAdmin = true,
                    requestingProfiles = listOf(targetProfile),
                    dialogType = SettingsViewModel.DialogType.DenyProfile,
                    selectedRequestingProfile = targetProfile
                ),
                onDismissDialog = { clicked = true }
            )
        }

        composeTestRule.onNodeWithText("Cancel").performClick()

        assertTrue(clicked)
    }

    // Admin section - Remove profile dialog -------------------------------------------------------

    @Test
    fun removeProfileDialog_displaysDisplayNameAndAccountName() {
        val targetProfile = SettingsViewModel.AllowedProfile(
            displayName = "Ben",
            accountName = "@ben.json",
            avatarModel = R.drawable.benjson
        )
        composeTestRule.setContent {
            SettingsContent(
                state = SettingsViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    displayName = "Drew",
                    accountName = "@drew654",
                    isAdmin = true,
                    allowedProfiles = listOf(targetProfile),
                    dialogType = SettingsViewModel.DialogType.RemoveProfile,
                    selectedAllowedProfile = targetProfile
                )
            )
        }

        composeTestRule.onNodeWithText("Ben").assertIsDisplayed()
        composeTestRule.onNodeWithText("@ben.json").assertIsDisplayed()
    }

    @Test
    fun clickRemoveProfileDialogRemove_triggersCallback() {
        var clicked = false
        val targetProfile = SettingsViewModel.AllowedProfile(
            displayName = "Ben",
            accountName = "@ben.json",
            avatarModel = R.drawable.benjson
        )
        composeTestRule.setContent {
            SettingsContent(
                state = SettingsViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    displayName = "Drew",
                    accountName = "@drew654",
                    isAdmin = true,
                    allowedProfiles = listOf(targetProfile),
                    dialogType = SettingsViewModel.DialogType.RemoveProfile,
                    selectedAllowedProfile = targetProfile
                ),
                onConfirmRemoveProfileClicked = { clicked = true }
            )
        }

        composeTestRule.onNodeWithText("Remove").performClick()

        assertTrue(clicked)
    }

    @Test
    fun clickRemoveProfileDialogCancel_triggersCallback() {
        var clicked = false
        val targetProfile = SettingsViewModel.AllowedProfile(
            displayName = "Ben",
            accountName = "@ben.json",
            avatarModel = R.drawable.benjson
        )
        composeTestRule.setContent {
            SettingsContent(
                state = SettingsViewModel.UiState(
                    serverUrl = "server.therealpercival.com",
                    displayName = "Drew",
                    accountName = "@drew654",
                    isAdmin = true,
                    allowedProfiles = listOf(targetProfile),
                    dialogType = SettingsViewModel.DialogType.RemoveProfile,
                    selectedAllowedProfile = targetProfile
                ),
                onDismissDialog = { clicked = true }
            )
        }

        composeTestRule.onNodeWithText("Cancel").performClick()

        assertTrue(clicked)
    }
}
