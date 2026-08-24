package com.therealpercival.avalon.domain.repository

import com.therealpercival.avalon.domain.model.GameSummary
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getGameSummary(): Flow<GameSummary>
}
