package com.iagocarvalho.activelife.data.repository

import com.iagocarvalho.activelife.domain.repository.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(private val dataStore: DataStoreOperations) {

    suspend fun saveOnBordingState(completed: Boolean){
        dataStore.saveOnBordingState(completed = completed)
    }

    fun readOnBoradingState(): Flow<Boolean>{
        return dataStore.readOnBorardingState()
    }
}