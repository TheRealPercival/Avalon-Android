package com.therealpercival.avalon.domain.repository

import com.therealpercival.avalon.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getCurrentUser(): Flow<User?>
    suspend fun signIn()
    suspend fun signOut()
}
