package com.therealpercival.avalon.data.repository

import com.therealpercival.avalon.R
import com.therealpercival.avalon.domain.model.GameSummary
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
}
