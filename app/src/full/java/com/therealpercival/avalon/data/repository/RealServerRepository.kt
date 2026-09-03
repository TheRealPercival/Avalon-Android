package com.therealpercival.avalon.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.therealpercival.avalon.domain.model.ConnectionStatus
import com.therealpercival.avalon.domain.model.ServerInfo
import com.therealpercival.avalon.domain.repository.ServerRepository
import com.therealpercival.avalon.domain.repository.UserRepository
import io.socket.client.IO
import io.socket.client.Socket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

@Singleton
class RealServerRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val json: Json,
    private val userRepositoryProvider: Provider<UserRepository>
) : ServerRepository {
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private var socket: Socket? = null

    private val _connectionStatus = MutableStateFlow(ConnectionStatus.DISCONNECTED)

    init {
        scope.launch {
            getServerUrl().collect { url ->
                if (url.isNotBlank()) {
                    connect()
                } else {
                    disconnect()
                }
            }
        }
    }

    companion object {
        const val TAG = "RealServerRepository"
    }

    private object PreferencesKeys {
        val SERVER_URL = stringPreferencesKey("server_url")
        val SERVER_INFO = stringPreferencesKey("server_info")
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

    private suspend fun saveServerInfo(info: ServerInfo) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.SERVER_INFO] = json.encodeToString(info)
        }
    }

    override suspend fun validateServerUrl(url: String): Boolean {
        Log.d(TAG, "Validating server URL: $url")
        val formattedUrl = if (!url.startsWith("http://") && !url.startsWith("https://")) {
            "https://$url"
        } else {
            url
        }
        Log.d(TAG, "Formatted URL for validation: $formattedUrl")

        return withTimeoutOrNull(5.seconds) {
            val opts = IO.Options.builder()
                .build()
            
            val tempSocket = IO.socket(formattedUrl, opts)
            var isValid = false
            
            tempSocket.on(Socket.EVENT_CONNECT) {
                Log.d(TAG, "Temp socket connected to $formattedUrl")
            }
            
            tempSocket.on(Socket.EVENT_CONNECT_ERROR) { args ->
                Log.e(TAG, "Temp socket connect error: ${args.firstOrNull()}")
            }

            tempSocket.on("info") {
                Log.d(TAG, "Received info from temp socket")
                isValid = true
                tempSocket.disconnect()
            }
            
            tempSocket.connect()
            
            while (!isValid && (tempSocket.connected() || tempSocket.id() == null)) {
                delay(100.milliseconds)
            }
            
            Log.d(TAG, "Validation result for $formattedUrl: $isValid")
            isValid
        } ?: run {
            Log.w(TAG, "Validation timed out for $url")
            false
        }
    }

    override fun getConnectionStatus(): Flow<ConnectionStatus> = _connectionStatus.asStateFlow()

    override fun getServerInfo(): Flow<ServerInfo?> {
        return dataStore.data.map { preferences ->
            val infoJson = preferences[PreferencesKeys.SERVER_INFO]
            if (infoJson != null) {
                try {
                    json.decodeFromString<ServerInfo>(infoJson)
                } catch (_: Exception) {
                    null
                }
            } else {
                null
            }
        }
    }

    override fun connect() {
        if (socket?.connected() == true) return

        scope.launch {
            val url = getServerUrl().first()
            if (url.isBlank()) {
                Log.w(TAG, "Connect called but server URL is blank")
                return@launch
            }

            val formattedUrl = if (!url.startsWith("http://") && !url.startsWith("https://")) {
                "https://$url"
            } else {
                url
            }

            Log.d(TAG, "Connecting to socket at $formattedUrl")
            _connectionStatus.value = ConnectionStatus.CONNECTING

            val tokens = userRepositoryProvider.get().getSessionTokens()
            val authPayload = tokens?.let {
                mapOf(
                    "access_token" to it.accessToken,
                    "refresh_token" to it.refreshToken
                )
            }

            val opts = IO.Options.builder()
                .apply {
                    if (authPayload != null) {
                        setAuth(authPayload)
                    }
                }
                .build()

            socket?.disconnect()
            socket = IO.socket(formattedUrl, opts).apply {
                on(Socket.EVENT_CONNECT) {
                    Log.d(TAG, "Socket connected")
                    _connectionStatus.value = ConnectionStatus.CONNECTED
                }
                on(Socket.EVENT_DISCONNECT) {
                    Log.d(TAG, "Socket disconnected")
                    _connectionStatus.value = ConnectionStatus.DISCONNECTED
                }
                on(Socket.EVENT_CONNECT_ERROR) { args ->
                    Log.e(TAG, "Socket connect error: ${args.firstOrNull()}")
                    _connectionStatus.value = ConnectionStatus.DISCONNECTED
                }
                on("info") { args ->
                    Log.d(TAG, "Received info event")
                    val data = args.firstOrNull()?.toString() ?: return@on
                    try {
                        val info = json.decodeFromString<ServerInfo>(data)
                        Log.d(TAG, "Server info parsed: $info")
                        scope.launch {
                            saveServerInfo(info)
                        }
                    } catch (e: Exception) {
                        Log.e(TAG, "Error parsing server info", e)
                    }
                }
                connect()
            }
        }
    }

    override fun disconnect() {
        socket?.disconnect()
        socket = null
        _connectionStatus.value = ConnectionStatus.DISCONNECTED
        scope.launch {
            dataStore.edit { preferences ->
                preferences.remove(PreferencesKeys.SERVER_INFO)
            }
        }
    }
}
