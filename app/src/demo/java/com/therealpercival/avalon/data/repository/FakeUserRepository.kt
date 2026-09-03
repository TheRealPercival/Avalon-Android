package com.therealpercival.avalon.data.repository

import android.content.Intent
import com.therealpercival.avalon.domain.model.AuthSession
import com.therealpercival.avalon.domain.model.User
import com.therealpercival.avalon.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeUserRepository @Inject constructor() : UserRepository {
    private val currentUser = MutableStateFlow<User?>(null)

    override fun getCurrentUser(): Flow<User?> = currentUser

    override fun isAuthenticating(): Flow<Boolean> = flowOf(false)

    override fun getSessionTokens(): AuthSession? = null

    override suspend fun signIn() {
        currentUser.value = User(
            displayName = "Drew",
            accountName = "@drew654",
            isAdmin = true
        )
    }

    override suspend fun signOut() {
        currentUser.value = null
    }

    override fun handleDeepLink(intent: Intent) {}
}
