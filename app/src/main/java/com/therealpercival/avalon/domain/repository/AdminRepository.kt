package com.therealpercival.avalon.domain.repository

import com.therealpercival.avalon.domain.model.AllowedProfile
import com.therealpercival.avalon.domain.model.RequestingProfile
import kotlinx.coroutines.flow.Flow

interface AdminRepository {
    fun getRequestingProfiles(): Flow<List<RequestingProfile>>
    fun getAllowedProfiles(): Flow<List<AllowedProfile>>
    suspend fun allowProfile(accountName: String, nickname: String)
    suspend fun denyProfile(accountName: String)
    suspend fun removeProfile(accountName: String)
}
