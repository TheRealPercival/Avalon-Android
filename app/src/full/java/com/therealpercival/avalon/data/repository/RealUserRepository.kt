package com.therealpercival.avalon.data.repository

import com.therealpercival.avalon.domain.model.User
import com.therealpercival.avalon.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RealUserRepository @Inject constructor() : UserRepository {
    override fun getCurrentUser(): Flow<User?> = flowOf(null)
    override suspend fun signIn() {}
    override suspend fun signOut() {}
}
