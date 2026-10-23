package com.therealpercival.avalon.domain.repository

import com.therealpercival.avalon.domain.model.GameSummary
import com.therealpercival.avalon.domain.model.LobbyData
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getGameSummary(): Flow<GameSummary>
    fun getLobbyData(): Flow<LobbyData>
}
