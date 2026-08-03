package com.therealpercival.avalon.presentation.settings

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.therealpercival.avalon.R
import com.therealpercival.avalon.presentation.Screen
import com.therealpercival.avalon.presentation.settings.components.AdminSection
import com.therealpercival.avalon.presentation.settings.components.BasicDialog
import com.therealpercival.avalon.presentation.ui.theme.AvalonNavBarThemePreview
import com.therealpercival.avalon.presentation.ui.theme.DayNightDevicePreviews

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()
    SettingsContent(
        state = state,
        onChangeServerClicked = {
            viewModel.showChangeServerDialog()
        },
        onConfirmChangeServerClicked = {
            viewModel.changeServer()
        },
        onSignOutClicked = {
            viewModel.showSignOutDialog()
        },
        onConfirmSignOutClicked = {
            viewModel.signOut()
        },
        onAllowClicked = {
            viewModel.showAssignNicknameDialog(it)
        },
        onDenyClicked = {
            viewModel.showDenyProfileDialog(it)
        },
        onConfirmDenyProfileClicked = {
            viewModel.denyProfile(state.selectedRequestingProfile!!)
        },
        onRemoveClicked = {
            viewModel.showRemoveProfileDialog(it)
        },
        onConfirmRemoveProfileClicked = {
            viewModel.removeProfile(state.selectedAllowedProfile!!)
        },
        onDismissDialog = {
            viewModel.dismissDialog()
        }
    )
}

@Composable
internal fun SettingsContent(
    state: SettingsViewModel.UiState,
    onChangeServerClicked: () -> Unit = { },
    onConfirmChangeServerClicked: () -> Unit = { },
    onSignOutClicked: () -> Unit = { },
    onConfirmSignOutClicked: () -> Unit = { },
    onAllowClicked: (SettingsViewModel.RequestingProfile) -> Unit = { },
    onDenyClicked: (SettingsViewModel.RequestingProfile) -> Unit = { },
    onConfirmDenyProfileClicked: () -> Unit = { },
    onRemoveClicked: (SettingsViewModel.AllowedProfile) -> Unit = { },
    onConfirmRemoveProfileClicked: () -> Unit = { },
    onDismissDialog: () -> Unit = { }
) {
    val disabledColors = OutlinedTextFieldDefaults.colors(
        disabledTextColor = MaterialTheme.colorScheme.onSurface,
        disabledBorderColor = MaterialTheme.colorScheme.outline,
        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledSuffixColor = MaterialTheme.colorScheme.onSurfaceVariant
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = spacedBy(16.dp)
    ) {
        if (state.isAdmin) {
            AdminSection(
                requestingProfiles = state.requestingProfiles,
                allowedProfiles = state.allowedProfiles,
                onAllowClicked = onAllowClicked,
                onDenyClicked = onDenyClicked,
                onRemoveClicked = onRemoveClicked
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )
        }

        OutlinedTextField(
            value = state.serverUrl,
            onValueChange = { },
            enabled = false,
            label = { Text("Server URL") },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_check_24),
                    contentDescription = "Server URL is valid"
                )
            },
            singleLine = true,
            maxLines = 1,
            colors = disabledColors,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                onChangeServerClicked()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError
            )
        ) {
            Text(text = "Change Server")
        }
        OutlinedTextField(
            value = state.displayName,
            onValueChange = { },
            enabled = false,
            label = { Text("Account") },
            suffix = { Text(state.accountName) },
            singleLine = true,
            maxLines = 1,
            colors = disabledColors,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                onSignOutClicked()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError
            )
        ) {
            Text(text = "Sign Out")
        }
    }

    when (state.dialogType) {
        is SettingsViewModel.DialogType.Server -> {
            BasicDialog(
                title = "Are you sure?",
                body = "Changing your current server will also sign you out.",
                confirmText = "Yes",
                dismissText = "Cancel",
                onConfirm = {
                    onConfirmChangeServerClicked()
                },
                onDismiss = {
                    onDismissDialog()
                }
            )
        }
        is SettingsViewModel.DialogType.SignOut -> {
            BasicDialog(
                title = "Are you sure?",
                body = "Do you really want to sign out?",
                confirmText = "Yes",
                dismissText = "Cancel",
                onConfirm = {
                    onConfirmSignOutClicked()
                },
                onDismiss = {
                    onDismissDialog()
                }
            )
        }
        is SettingsViewModel.DialogType.AssignNickname -> {

        }
        is SettingsViewModel.DialogType.DenyProfile -> {
            BasicDialog(
                title = "Are you sure?",
                body = "Do you want to reject ${state.selectedRequestingProfile?.accountName} from joining the server?",
                confirmText = "Reject",
                dismissText = "Cancel",
                onConfirm = {
                    onConfirmDenyProfileClicked()
                },
                onDismiss = {
                    onDismissDialog()
                }
            )
        }
        is SettingsViewModel.DialogType.RemoveProfile -> {
            BasicDialog(
                title = "Are you sure?",
                body = "Doy you want to remove ${state.selectedAllowedProfile?.displayName} (${state.selectedAllowedProfile?.accountName}) from the server?",
                confirmText = "Remove",
                dismissText = "Cancel",
                onConfirm = {
                    onConfirmRemoveProfileClicked()
                },
                onDismiss = {
                    onDismissDialog()
                }
            )
        }
        null -> { }
    }
}

@DayNightDevicePreviews
@Composable
private fun SettingsScreenPreview() {
    AvalonNavBarThemePreview(currentRoute = Screen.Settings.route) {
        SettingsContent(
            state = SettingsViewModel.UiState(
                serverUrl = "server.therealpercival.com",
                displayName = "Drew",
                accountName = "@drew654"
            )
        )
    }
}

@DayNightDevicePreviews
@Composable
private fun SettingsScreenAdminPreview() {
    AvalonNavBarThemePreview(currentRoute = Screen.Settings.route) {
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
                    ),
                    SettingsViewModel.RequestingProfile(
                        accountName = "@_shoe_",
                        avatarModel = R.drawable._shoe_
                    )
                ),
                allowedProfiles = listOf(
                    SettingsViewModel.AllowedProfile(
                        displayName = "Landon",
                        accountName = "@landon248",
                        avatarModel = R.drawable.landon248
                    ),
                    SettingsViewModel.AllowedProfile(
                        displayName = "Izzy",
                        accountName = "@izzyderose",
                        avatarModel = R.drawable.izzyderose
                    )
                )
            )
        )
    }
}

@DayNightDevicePreviews
@Composable
private fun SettingsScreenServerDialogPreview() {
    AvalonNavBarThemePreview(currentRoute = Screen.Settings.route) {
        SettingsContent(
            state = SettingsViewModel.UiState(
                serverUrl = "server.therealpercival.com",
                displayName = "Drew",
                accountName = "@drew654",
                dialogType = SettingsViewModel.DialogType.Server
            )
        )
    }
}

@DayNightDevicePreviews
@Composable
private fun SettingsScreenSignOutDialogPreview() {
    AvalonNavBarThemePreview(currentRoute = Screen.Settings.route) {
        SettingsContent(
            state = SettingsViewModel.UiState(
                serverUrl = "server.therealpercival.com",
                displayName = "Drew",
                accountName = "@drew654",
                dialogType = SettingsViewModel.DialogType.SignOut
            )
        )
    }
}

@DayNightDevicePreviews
@Composable
private fun SettingsScreenRejectProfileDialogPreview() {
    AvalonNavBarThemePreview(currentRoute = Screen.Settings.route) {
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
                    ),
                    SettingsViewModel.RequestingProfile(
                        accountName = "@_shoe_",
                        avatarModel = R.drawable._shoe_
                    )
                ),
                dialogType = SettingsViewModel.DialogType.DenyProfile,
                selectedRequestingProfile = SettingsViewModel.RequestingProfile(
                    accountName = "@ben.json",
                    avatarModel = R.drawable.benjson
                )
            )
        )
    }
}

@DayNightDevicePreviews
@Composable
private fun SettingsScreenRemoveProfilePreview() {
    AvalonNavBarThemePreview(currentRoute = Screen.Settings.route) {
        SettingsContent(
            state = SettingsViewModel.UiState(
                serverUrl = "server.therealpercival.com",
                displayName = "Drew",
                accountName = "@drew654",
                isAdmin = true,
                allowedProfiles = listOf(
                    SettingsViewModel.AllowedProfile(
                        displayName = "Landon",
                        accountName = "@landon248",
                        avatarModel = R.drawable.landon248
                    ),
                    SettingsViewModel.AllowedProfile(
                        displayName = "Izzy",
                        accountName = "@izzyderose",
                        avatarModel = R.drawable.izzyderose
                    )
                ),
                dialogType = SettingsViewModel.DialogType.RemoveProfile,
                selectedAllowedProfile = SettingsViewModel.AllowedProfile(
                    displayName = "Landon",
                    accountName = "@landon248",
                    avatarModel = R.drawable.landon248
                )
            )
        )
    }
}
