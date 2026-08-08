package com.therealpercival.avalon.data.repository

import com.therealpercival.avalon.R
import com.therealpercival.avalon.domain.model.AllowedProfile
import com.therealpercival.avalon.domain.model.RequestingProfile
import com.therealpercival.avalon.domain.repository.AdminRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeAdminRepository @Inject constructor() : AdminRepository {
    private val requestingProfiles = MutableStateFlow(
        listOf(
            RequestingProfile(accountName = "@ben.json", avatarModel = R.drawable.benjson),
            RequestingProfile(accountName = "@_shoe_", avatarModel = R.drawable._shoe_)
        )
    )

    private val allowedProfiles = MutableStateFlow(
        listOf(
            AllowedProfile(displayName = "Landon", accountName = "@landon248", avatarModel = R.drawable.landon248),
            AllowedProfile(displayName = "Izzy", accountName = "@izzyderose", avatarModel = R.drawable.izzyderose)
        )
    )

    override fun getRequestingProfiles(): Flow<List<RequestingProfile>> = requestingProfiles

    override fun getAllowedProfiles(): Flow<List<AllowedProfile>> = allowedProfiles

    override suspend fun allowProfile(accountName: String, nickname: String) {
        val profile = requestingProfiles.value.find { it.accountName == accountName }
        if (profile != null) {
            requestingProfiles.update { list -> list.filterNot { it.accountName == accountName } }
            allowedProfiles.update { list ->
                list + AllowedProfile(
                    displayName = nickname,
                    accountName = accountName,
                    avatarModel = profile.avatarModel
                )
            }
        }
    }

    override suspend fun denyProfile(accountName: String) {
        requestingProfiles.update { list -> list.filterNot { it.accountName == accountName } }
    }

    override suspend fun removeProfile(accountName: String) {
        allowedProfiles.update { list -> list.filterNot { it.accountName == accountName } }
    }
}
