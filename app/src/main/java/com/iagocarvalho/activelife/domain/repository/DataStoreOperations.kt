package com.iagocarvalho.activelife.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    suspend fun saveOnBordingState(completed: Boolean)
    fun readOnBorardingState(): Flow<Boolean>
}