package com.therealpercival.avalon.data.repository

import com.therealpercival.avalon.domain.model.GameSummary
import com.therealpercival.avalon.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RealGameRepository @Inject constructor() : GameRepository {
    override fun getGameSummary(): Flow<GameSummary> = flowOf(
        GameSummary(
            joinText = "Join game",
            playerAvatars = emptyList()
        )
    )
}
