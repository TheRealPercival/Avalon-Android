package com.therealpercival.avalon.data.repository

import android.content.Intent
import android.util.Log
import com.therealpercival.avalon.domain.model.User
import com.therealpercival.avalon.domain.repository.ServerRepository
import com.therealpercival.avalon.domain.repository.UserRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.handleDeeplinks
import io.github.jan.supabase.auth.providers.Discord
import io.github.jan.supabase.auth.status.SessionStatus
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RealUserRepository @Inject constructor(
    private val serverRepository: ServerRepository
) : UserRepository {
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private var supabaseClient: SupabaseClient? = null
    private var authStateJob: Job? = null
    private var pendingIntent: Intent? = null

    private val _currentUser = MutableStateFlow<User?>(null)
    private val _isAuthenticating = MutableStateFlow(false)

    init {
        serverRepository.getServerInfo()
            .distinctUntilChanged()
            .onEach { serverInfo ->
                Log.d(TAG, "Received server info: $serverInfo")
                if (serverInfo != null) {
                    try {
                        supabaseClient = createSupabaseClient(
                            supabaseUrl = serverInfo.supabaseURL,
                            supabaseKey = serverInfo.supabaseAnonKey
                        ) {
                            install(Auth) {
                                scheme = "avalontrp"
                                host = "setup"
                            }
                            install(Postgrest)
                        }
                        Log.d(TAG, "Supabase client initialized")
                        observeAuthState()

                        pendingIntent?.let {
                            Log.d(TAG, "Handling pending deep link")
                            handleDeepLink(it)
                            pendingIntent = null
                        }
                    } catch (e: Exception) {
                        Log.e(TAG, "Error initializing Supabase client", e)
                    }
                } else {
                    authStateJob?.cancel()
                    supabaseClient = null
                    _currentUser.value = null
                    _isAuthenticating.value = false
                }
            }
            .launchIn(scope)
    }

    companion object {
        const val TAG = "RealUserRepository"
    }

    private fun observeAuthState() {
        val client = supabaseClient ?: return
        Log.d(TAG, "Observing auth state")
        authStateJob?.cancel()
        authStateJob = client.auth.sessionStatus
            .onEach { status ->
                Log.d(TAG, "Supabase auth status changed: $status")
                when (status) {
                    is SessionStatus.Authenticated -> {
                        _isAuthenticating.value = false
                        val supabaseUser = status.session.user
                        Log.d(TAG, "User authenticated: ${supabaseUser?.email}")
                        if (supabaseUser != null) {
                            val fullName = supabaseUser.userMetadata?.get("full_name")
                                ?.toString()?.removeSurrounding("\"") ?: "Unknown"
                            _currentUser.value = User(
                                displayName = fullName,
                                accountName = "@${fullName.lowercase().replace(" ", ".")}",
                                isAdmin = false
                            )
                        }
                    }

                    is SessionStatus.NotAuthenticated -> {
                        _isAuthenticating.value = false
                        _currentUser.value = null
                    }

                    else -> {}
                }
            }
            .launchIn(scope)
    }

    override fun getCurrentUser(): Flow<User?> = _currentUser.asStateFlow()

    override fun isAuthenticating(): Flow<Boolean> = _isAuthenticating.asStateFlow()

    override suspend fun signIn() {
        Log.d(TAG, "signIn called")
        _isAuthenticating.value = true
        supabaseClient?.auth?.signInWith(Discord)
    }

    override suspend fun signOut() {
        supabaseClient?.auth?.signOut()
    }

    override fun handleDeepLink(intent: Intent) {
        val uri = intent.data
        Log.d(TAG, "Handling deep link: ${uri?.toString()}")

        if (supabaseClient == null) {
            Log.d(TAG, "Client not ready, storing pending intent")
            pendingIntent = intent
            return
        }

        if (uri?.scheme == "avalontrp" && uri.host == "setup") {
            _isAuthenticating.value = true
        }

        supabaseClient?.handleDeeplinks(intent)
    }
}
