package com.therealpercival.avalon.data.repository

import com.therealpercival.avalon.domain.model.ConnectionStatus
import com.therealpercival.avalon.domain.model.ServerInfo
import com.therealpercival.avalon.domain.repository.ServerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeServerRepository @Inject constructor() : ServerRepository {
    private val _serverUrl = MutableStateFlow("")
    private val _serverInfo = MutableStateFlow<ServerInfo?>(null)
    
    override fun getServerUrl(): Flow<String> = _serverUrl.asStateFlow()

    override suspend fun saveServerUrl(url: String) {
        _serverUrl.value = url
    }

    override suspend fun validateServerUrl(url: String): Boolean {
        return true
    }

    override fun getConnectionStatus(): Flow<ConnectionStatus> = flowOf(ConnectionStatus.CONNECTED)

    override fun getServerInfo(): Flow<ServerInfo?> = _serverInfo.asStateFlow()

    override fun connect() {
        _serverInfo.value = ServerInfo(
            version = "1.0.0",
            supabaseURL = "https://fake.supabase.co",
            supabaseAnonKey = "fake_key"
        )
    }

    override fun disconnect() {
        _serverInfo.value = null
    }
}
