package com.therealpercival.avalon.data.repository

import com.therealpercival.avalon.domain.model.AllowedProfile
import com.therealpercival.avalon.domain.model.RequestingProfile
import com.therealpercival.avalon.domain.repository.AdminRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RealAdminRepository @Inject constructor() : AdminRepository {
    override fun getRequestingProfiles(): Flow<List<RequestingProfile>> = flowOf(emptyList())
    override fun getAllowedProfiles(): Flow<List<AllowedProfile>> = flowOf(emptyList())
    override suspend fun allowProfile(accountName: String, nickname: String) {}
    override suspend fun denyProfile(accountName: String) {}
    override suspend fun removeProfile(accountName: String) {}
}
