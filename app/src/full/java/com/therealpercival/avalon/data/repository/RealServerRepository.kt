package com.therealpercival.avalon.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.therealpercival.avalon.domain.repository.ServerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RealServerRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : ServerRepository {
    private object PreferencesKeys {
        val SERVER_URL = stringPreferencesKey("server_url")
    }

    override fun getServerUrl(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[PreferencesKeys.SERVER_URL] ?: ""
        }
    }

    override suspend fun saveServerUrl(url: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.SERVER_URL] = url
        }
    }

    override suspend fun validateServerUrl(url: String): Boolean {
        return false
    }
}
