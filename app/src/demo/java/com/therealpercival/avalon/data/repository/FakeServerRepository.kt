package com.therealpercival.avalon.data.repository

import com.therealpercival.avalon.domain.repository.ServerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeServerRepository @Inject constructor() : ServerRepository {
    private val _serverUrl = MutableStateFlow("")
    
    override fun getServerUrl(): Flow<String> = _serverUrl.asStateFlow()

    override suspend fun saveServerUrl(url: String) {
        _serverUrl.value = url
    }
}
