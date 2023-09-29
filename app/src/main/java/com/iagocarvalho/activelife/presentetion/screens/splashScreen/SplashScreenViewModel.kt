package com.iagocarvalho.activelife.presentetion.screens.splashScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagocarvalho.activelife.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(private val useCases: UseCases) : ViewModel() {

    private val _onBordingCompleted = MutableStateFlow(false)
    val onboardingCompleted: StateFlow<Boolean> = _onBordingCompleted

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _onBordingCompleted.value =
                useCases.readOnBoradingUseCase().stateIn(viewModelScope).value
        }
    }
}