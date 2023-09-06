package com.iagocarvalho.activelife.screens.loginScreens.emailValidation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagocarvalho.activelife.firebaserepository.AuthRepository
import kotlinx.coroutines.launch

class EmailVerificationScreenViewModel: ViewModel() {
    fun SendEmailVerification(){
        viewModelScope.launch { AuthRepository().SendEmailVerification() }
    }
}