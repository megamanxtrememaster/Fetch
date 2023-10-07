package com.cebolledo.fetch.di

import com.cebolledo.fetch.database.DataDao
import com.cebolledo.fetch.database.di.IoDispatcher
import com.cebolledo.fetch.network.service.FetchClient
import com.cebolledo.fetch.repository.Repository
import com.cebolledo.fetch.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {

    @Provides
    @Singleton
    fun provideRepository(
        fetchClient: FetchClient,
        dataDao: DataDao,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): Repository {
        return RepositoryImpl(fetchClient, dataDao, ioDispatcher)
    }
}