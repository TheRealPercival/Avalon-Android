package com.therealpercival.avalon.presentation.settings.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.therealpercival.avalon.R
import com.therealpercival.avalon.presentation.settings.SettingsViewModel
import com.therealpercival.avalon.presentation.ui.theme.DayNightPreviews
import com.therealpercival.avalon.presentation.ui.theme.ThemePreview

@Composable
fun AllowedProfileListItem(
    profile: SettingsViewModel.AllowedProfile,
    modifier: Modifier = Modifier,
    onRemoveClicked: (SettingsViewModel.AllowedProfile) -> Unit = { }
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.extraSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = profile.avatarModel,
            contentDescription = "Avatar",
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .size(32.dp)
                .clip(CircleShape)
        )
        Text(
            text = profile.displayName,
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 16.dp)
        )
        Text(
            text = profile.accountName,
            modifier = Modifier.padding(end = 4.dp, top = 16.dp, bottom = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = {
                onRemoveClicked(profile)
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.outline_cancel_24),
                contentDescription = "Remove"
            )
        }
    }
}

@DayNightPreviews
@Composable
private fun AllowedProfileListItemPreview() {
    ThemePreview {
        AllowedProfileListItem(
            profile = SettingsViewModel.AllowedProfile(
                displayName = "Drew",
                accountName = "@drew654",
                avatarModel = R.drawable.x
            )
        )
    }
}
