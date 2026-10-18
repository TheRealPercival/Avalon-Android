package com.therealpercival.avalon.presentation.lobby

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.therealpercival.avalon.R
import com.therealpercival.avalon.domain.model.AvalonCharacter
import com.therealpercival.avalon.presentation.components.DropdownInputField
import com.therealpercival.avalon.presentation.lobby.components.EditRolesSheetBody
import com.therealpercival.avalon.presentation.lobby.components.LadyOfTheLakeButton
import com.therealpercival.avalon.presentation.lobby.components.PlayersSection
import com.therealpercival.avalon.presentation.lobby.components.RolesSection
import com.therealpercival.avalon.presentation.lobby.components.TrapperButton
import com.therealpercival.avalon.presentation.lobby.components.VoteResetButton
import com.therealpercival.avalon.presentation.ui.WindowHeightSizeClass
import com.therealpercival.avalon.presentation.ui.rememberWindowSizeClass
import com.therealpercival.avalon.presentation.ui.theme.DayNightDevicePreviews
import com.therealpercival.avalon.presentation.ui.theme.DeviceThemePreview
import com.therealpercival.avalon.presentation.ui.theme.LayoutPreviews

@Composable
fun LobbyScreen(
    viewModel: LobbyViewModel = hiltViewModel(),
    onBackClicked: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LobbyContent(
        state = uiState,
        onBackClicked = onBackClicked,
        onPresetSelected = viewModel::onPresetSelected,
        onPresetExpandedChange = viewModel::onPresetExpandedChange,
        onRolesSectionClicked = viewModel::onRolesSectionClicked,
        onCharacterSheetDismissed = viewModel::onCharacterSheetDismissed,
        onCharacterCardClicked = viewModel::onCharacterCardClicked,
        onCharacterCardLongClicked = viewModel::onCharacterCardLongClicked,
        onFocusedCharacterDismissed = viewModel::onFocusedCharacterDismissed,
        onAssassinSelected = viewModel::onAssassinSelected,
        onAssassinDropdownExpandedChange = viewModel::onAssassinDropdownExpandedChange,
        onTrapperClicked = viewModel::onTrapperClicked,
        onLadyOfTheLakeClicked = viewModel::onLadyOfTheLakeClicked,
        onVoteResetClicked = viewModel::onVoteResetClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LobbyContent(
    state: LobbyViewModel.UiState,
    onBackClicked: () -> Unit = { },
    onPresetSelected: (String) -> Unit = { },
    onPresetExpandedChange: (Boolean) -> Unit = { },
    onRolesSectionClicked: () -> Unit = { },
    onCharacterSheetDismissed: () -> Unit = { },
    onCharacterCardClicked: (AvalonCharacter) -> Unit = { },
    onCharacterCardLongClicked: (AvalonCharacter) -> Unit = { },
    onFocusedCharacterDismissed: () -> Unit = { },
    onAssassinSelected: (AvalonCharacter) -> Unit = { },
    onAssassinDropdownExpandedChange: (Boolean) -> Unit = { },
    onTrapperClicked: () -> Unit = { },
    onLadyOfTheLakeClicked: () -> Unit = { },
    onVoteResetClicked: () -> Unit = { }
) {
    val characterSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val windowSizeClass = rememberWindowSizeClass()
    val isCompactHeight = windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Lobby") },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClicked
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        val scrollState = rememberScrollState()

        if (isCompactHeight) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(scrollState)
                    .padding(all = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    DropdownInputField(
                        value = state.selectedPreset,
                        onValueChange = onPresetSelected,
                        options = state.presetOptions,
                        label = "Preset",
                        isExpanded = state.isPresetDropdownExpanded,
                        onExpandedChange = onPresetExpandedChange
                    )
                    RolesSection(
                        selectedCharacters = state.selectedCharacters,
                        onClick = onRolesSectionClicked
                    )
                    SettingsSection(
                        isTrapperEnabled = state.isTrapperEnabled,
                        isLadyOfTheLakeEnabled = state.isLadyOfTheLakeEnabled,
                        isVoteResetEnabled = state.isVoteResetEnabled,
                        onTrapperClicked = onTrapperClicked,
                        onLadyOfTheLakeClicked = onLadyOfTheLakeClicked,
                        onVoteResetClicked = onVoteResetClicked
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    PlayersSection(
                        players = state.players
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(scrollState)
                    .padding(all = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                DropdownInputField(
                    value = state.selectedPreset,
                    onValueChange = onPresetSelected,
                    options = state.presetOptions,
                    label = "Preset",
                    isExpanded = state.isPresetDropdownExpanded,
                    onExpandedChange = onPresetExpandedChange
                )
                RolesSection(
                    selectedCharacters = state.selectedCharacters,
                    onClick = onRolesSectionClicked
                )
                SettingsSection(
                    isTrapperEnabled = state.isTrapperEnabled,
                    isLadyOfTheLakeEnabled = state.isLadyOfTheLakeEnabled,
                    isVoteResetEnabled = state.isVoteResetEnabled,
                    onTrapperClicked = onTrapperClicked,
                    onLadyOfTheLakeClicked = onLadyOfTheLakeClicked,
                    onVoteResetClicked = onVoteResetClicked
                )
                PlayersSection(
                    players = state.players
                )
            }
        }
    }

    if (state.isShowingCharacterSheet) {
        ModalBottomSheet(
            onDismissRequest = onCharacterSheetDismissed,
            sheetState = characterSheetState
        ) {
            EditRolesSheetBody(
                selectedCharacters = state.selectedCharacters,
                selectedAssassin = state.selectedAssassin,
                isAssassinDropdownExpanded = state.isAssassinDropdownExpanded,
                focusedCharacter = state.focusedCharacter,
                onCharacterCardClicked = onCharacterCardClicked,
                onCharacterCardLongClicked = onCharacterCardLongClicked,
                onFocusedCharacterDismissed = onFocusedCharacterDismissed,
                onAssassinSelected = onAssassinSelected,
                onAssassinDropdownExpandedChange = onAssassinDropdownExpandedChange
            )
        }
    }
}

@Composable
private fun SettingsSection(
    isTrapperEnabled: Boolean,
    isLadyOfTheLakeEnabled: Boolean,
    isVoteResetEnabled: Boolean,
    onTrapperClicked: () -> Unit,
    onLadyOfTheLakeClicked: () -> Unit,
    onVoteResetClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.labelSmall
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TrapperButton(
                onClick = onTrapperClicked,
                isSelected = isTrapperEnabled
            )
            LadyOfTheLakeButton(
                onClick = onLadyOfTheLakeClicked,
                isSelected = isLadyOfTheLakeEnabled
            )
            VoteResetButton(
                onClick = onVoteResetClicked,
                isSelected = isVoteResetEnabled
            )
        }
    }
}

@DayNightDevicePreviews
@LayoutPreviews
@Composable
private fun LobbyScreenPreview() {
    DeviceThemePreview {
        LobbyContent(
            state = LobbyViewModel.UiState(
                selectedPreset = "Classic",
                selectedCharacters = listOf(
                    AvalonCharacter.Merlin,
                    AvalonCharacter.Percival,
                    AvalonCharacter.LoyalServantOfArthur1,
                    AvalonCharacter.LoyalServantOfArthur2,
                    AvalonCharacter.LoyalServantOfArthur3,
                    AvalonCharacter.Morgana,
                    AvalonCharacter.Mordred,
                    AvalonCharacter.MinionOfMordred1
                ),
                presetOptions = listOf("Classic"),
                players = listOf(
                    LobbyViewModel.Player(
                        displayName = "Drew",
                        avatarModel = R.drawable.x
                    ),
                    LobbyViewModel.Player(
                        displayName = "Ben",
                        avatarModel = R.drawable.benjson
                    ),
                    LobbyViewModel.Player(
                        displayName = "Izzy",
                        avatarModel = R.drawable.izzyderose
                    ),
                    LobbyViewModel.Player(
                        displayName = "Thomas",
                        avatarModel = R.drawable._shoe_
                    ),
                    LobbyViewModel.Player(
                        displayName = "Landon",
                        avatarModel = R.drawable.landon248
                    )
                )
            )
        )
    }
}
