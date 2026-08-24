package com.therealpercival.avalon.domain.repository

import kotlinx.coroutines.flow.Flow

interface ServerRepository {
    fun getServerUrl(): Flow<String>
    suspend fun saveServerUrl(url: String)
    suspend fun validateServerUrl(url: String): Boolean
}
