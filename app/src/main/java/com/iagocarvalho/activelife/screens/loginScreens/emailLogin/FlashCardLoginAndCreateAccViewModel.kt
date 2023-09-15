package com.iagocarvalho.activelife.screens.loginScreens.emailLogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagocarvalho.activelife.firebaserepository.AuthRepository
import kotlinx.coroutines.launch

class FlashCardLoginAndCreateAccViewModel() : ViewModel() {


    fun FirebaseSignInWithEmailAndPasswordViewModel(
        email: String,
        senha: String,
        home: () -> Unit,
        errors: (Exception?) -> Unit
    ) {
        viewModelScope.launch {
            AuthRepository().firebaseSignInWithEmailAndPassword(
                email,
                senha,
                home,
                errors
            )
        }
    }

    fun createUserWithEmailAndPassword(
        name: String,
        peso: String,
        altura: String,
        idade: String,
        email: String,
        senha: String,
        home: () -> Unit,
        errors: (Exception?) -> Unit
    ) {
        viewModelScope.launch {
            AuthRepository().createUserWithEmailAndPassword(
                name,
                peso,
                altura,
                idade,
                email,
                senha,
                home,
                errors
            )
        }
    }
}