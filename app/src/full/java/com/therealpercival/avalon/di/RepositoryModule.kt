package com.therealpercival.avalon.di

import com.therealpercival.avalon.data.repository.RealAdminRepository
import com.therealpercival.avalon.data.repository.RealGameRepository
import com.therealpercival.avalon.data.repository.RealServerRepository
import com.therealpercival.avalon.data.repository.RealUserRepository
import com.therealpercival.avalon.domain.repository.AdminRepository
import com.therealpercival.avalon.domain.repository.GameRepository
import com.therealpercival.avalon.domain.repository.ServerRepository
import com.therealpercival.avalon.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindServerRepository(
        realServerRepository: RealServerRepository
    ): ServerRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        realUserRepository: RealUserRepository
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindAdminRepository(
        realAdminRepository: RealAdminRepository
    ): AdminRepository

    @Binds
    @Singleton
    abstract fun bindGameRepository(
        realGameRepository: RealGameRepository
    ): GameRepository
}
