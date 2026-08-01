package com.therealpercival.avalon.presentation.settings.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.therealpercival.avalon.R
import com.therealpercival.avalon.presentation.settings.SettingsViewModel
import com.therealpercival.avalon.presentation.ui.theme.DayNightPreviews
import com.therealpercival.avalon.presentation.ui.theme.ThemePreview

@Composable
fun AdminSection(
    requestingProfiles: List<SettingsViewModel.RequestingProfile> = emptyList(),
    allowedProfiles: List<SettingsViewModel.AllowedProfile> = emptyList(),
    onAllowClicked: (SettingsViewModel.RequestingProfile) -> Unit = { },
    onDenyClicked: (SettingsViewModel.RequestingProfile) -> Unit = { },
    onRemoveClicked: (SettingsViewModel.AllowedProfile) -> Unit = { }
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Requests",
            modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp),
            maxLines = 1,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        requestingProfiles.forEach { profile ->
            RequestingProfileListItem(
                profile = profile,
                modifier = Modifier.padding(bottom = 8.dp),
                onAllowClicked = onAllowClicked,
                onDenyClicked = onDenyClicked
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Accepted",
            modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp),
            maxLines = 1,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        allowedProfiles.forEach { profile ->
            AllowedProfileListItem(
                profile = profile,
                onRemoveClicked = onRemoveClicked
            )
        }
    }
}

@DayNightPreviews
@Composable
private fun AdminSectionPreview() {
    ThemePreview {
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
}
