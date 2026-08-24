package com.therealpercival.avalon.di

import com.therealpercival.avalon.data.repository.FakeAdminRepository
import com.therealpercival.avalon.data.repository.FakeGameRepository
import com.therealpercival.avalon.data.repository.FakeServerRepository
import com.therealpercival.avalon.data.repository.FakeUserRepository
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
        fakeServerRepository: FakeServerRepository
    ): ServerRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        fakeUserRepository: FakeUserRepository
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindAdminRepository(
        fakeAdminRepository: FakeAdminRepository
    ): AdminRepository

    @Binds
    @Singleton
    abstract fun bindGameRepository(
        fakeGameRepository: FakeGameRepository
    ): GameRepository
}
