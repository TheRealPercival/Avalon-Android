package com.therealpercival.avalon.data.repository

import com.therealpercival.avalon.R
import com.therealpercival.avalon.domain.model.AllowedProfile
import com.therealpercival.avalon.domain.model.AvalonCharacter
import com.therealpercival.avalon.domain.model.GameSummary
import com.therealpercival.avalon.domain.model.LobbyData
import com.therealpercival.avalon.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeGameRepository @Inject constructor() : GameRepository {
    override fun getGameSummary(): Flow<GameSummary> = flowOf(
        GameSummary(
            joinText = "Join game (5 in lobby)",
            playerAvatars = listOf(R.drawable.x, R.drawable.benjson, R.drawable._shoe_)
        )
    )

    override fun getLobbyData(): Flow<LobbyData> = flowOf(
        LobbyData(
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
                AllowedProfile(
                    displayName = "Drew",
                    accountName = "drew654",
                    avatarModel = R.drawable.x
                ),
                AllowedProfile(
                    displayName = "Ben",
                    accountName = "ben.json",
                    avatarModel = R.drawable.benjson
                ),
                AllowedProfile(
                    displayName = "Izzy",
                    accountName = "izzyderose",
                    avatarModel = R.drawable.izzyderose
                ),
                AllowedProfile(
                    displayName = "Thomas",
                    accountName = "_shoe_",
                    avatarModel = R.drawable._shoe_
                ),
                AllowedProfile(
                    displayName = "Landon",
                    accountName = "landon248",
                    avatarModel = R.drawable.landon248
                )
            )
        )
    )
}
