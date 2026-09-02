package com.therealpercival.avalon.domain.repository

import com.therealpercival.avalon.domain.model.ConnectionStatus
import com.therealpercival.avalon.domain.model.ServerInfo
import kotlinx.coroutines.flow.Flow

interface ServerRepository {
    fun getServerUrl(): Flow<String>
    suspend fun saveServerUrl(url: String)
    suspend fun validateServerUrl(url: String): Boolean
    fun getConnectionStatus(): Flow<ConnectionStatus>
    fun getServerInfo(): Flow<ServerInfo?>
    fun connect()
    fun disconnect()
}
