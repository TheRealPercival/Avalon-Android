package com.therealpercival.avalon.domain.repository

import android.content.Intent
import com.therealpercival.avalon.domain.model.AuthSession
import com.therealpercival.avalon.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getCurrentUser(): Flow<User?>
    fun isAuthenticating(): Flow<Boolean>
    fun getSessionTokens(): AuthSession?
    suspend fun signIn()
    suspend fun signOut()
    fun handleDeepLink(intent: Intent)
}
