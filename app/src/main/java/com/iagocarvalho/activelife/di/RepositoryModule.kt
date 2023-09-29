package com.iagocarvalho.activelife.di

import android.content.Context
import com.iagocarvalho.activelife.data.repository.DataStoreOperationImpl
import com.iagocarvalho.activelife.data.repository.Repository
import com.iagocarvalho.activelife.domain.repository.DataStoreOperations
import com.iagocarvalho.activelife.domain.use_cases.UseCases
import com.iagocarvalho.activelife.domain.use_cases.read_onbording.ReadOnBoradingUseCase
import com.iagocarvalho.activelife.domain.use_cases.save_onbording.SaveOnBoradingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun ProvideDataStoreOperation(@ApplicationContext context: Context): DataStoreOperations {
        return DataStoreOperationImpl(context = context)

    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            saveOnBoradingUseCase = SaveOnBoradingUseCase(repository),
            readOnBoradingUseCase = ReadOnBoradingUseCase(repository)
        )
    }
}