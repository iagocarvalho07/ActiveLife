package com.iagocarvalho.activelife.domain.use_cases

import com.iagocarvalho.activelife.domain.use_cases.read_onbording.ReadOnBoradingUseCase
import com.iagocarvalho.activelife.domain.use_cases.save_onbording.SaveOnBoradingUseCase

data class UseCases(
    val saveOnBoradingUseCase: SaveOnBoradingUseCase,
    val readOnBoradingUseCase: ReadOnBoradingUseCase
)