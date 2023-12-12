package com.pluto.bostausers.di

import com.pluto.bostausers.data.UserRepositoryImp
import com.pluto.bostausers.domain.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(repository: UserRepositoryImp): UserRepository
}