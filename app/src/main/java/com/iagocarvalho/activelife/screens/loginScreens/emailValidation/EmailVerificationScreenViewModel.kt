package com.iagocarvalho.activelife.screens.loginScreens.emailValidation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagocarvalho.activelife.firebaserepository.AuthRepository
import kotlinx.coroutines.launch
import okhttp3.internal.wait

class EmailVerificationScreenViewModel: ViewModel() {
    fun SendEmailVerification(){
        viewModelScope.launch { AuthRepository().SendEmailVerification() }
    }
}