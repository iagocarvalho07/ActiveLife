package com.iagocarvalho.activelife.domain.use_cases.read_onbording

import com.iagocarvalho.activelife.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnBoradingUseCase(private val repository: Repository) {
    operator fun invoke():Flow<Boolean>{
        return repository.readOnBoradingState()
    }
}