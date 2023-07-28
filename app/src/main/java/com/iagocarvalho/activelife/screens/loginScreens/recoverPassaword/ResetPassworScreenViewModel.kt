package com.iagocarvalho.activelife.screens.loginScreens.recoverPassaword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagocarvalho.activelife.firebaserepository.AuthRepository
import kotlinx.coroutines.launch

class ResetPassworScreenViewModel : ViewModel() {

    fun Resetpassword(email: String, home: () -> Unit, errors: (Exception?) -> Unit) {
        viewModelScope.launch { AuthRepository().sendPasswordResetEmail(email, home, errors) }
    }
}