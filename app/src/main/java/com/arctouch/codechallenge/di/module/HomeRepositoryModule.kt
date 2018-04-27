package com.arctouch.codechallenge.di.module

import com.arctouch.codechallenge.api.Endpoints
import com.arctouch.codechallenge.api.repository.HomeRepository
import com.arctouch.codechallenge.api.repository.HomeRepositoryImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HomeRepositoryModule {

    @Singleton
    @Provides
    fun providesHomeRepository(endpoints: Endpoints) : HomeRepository = HomeRepositoryImp(endpoints)
}
