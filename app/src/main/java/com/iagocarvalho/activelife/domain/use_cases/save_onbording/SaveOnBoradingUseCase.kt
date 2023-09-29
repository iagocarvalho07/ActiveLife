package com.iagocarvalho.activelife.domain.use_cases.save_onbording

import com.iagocarvalho.activelife.data.repository.Repository

class SaveOnBoradingUseCase(private val repository: Repository) {
    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBordingState(completed = completed)
    }
}