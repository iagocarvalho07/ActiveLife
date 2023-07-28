package com.iagocarvalho.activelife.screens.loginScreens.EmailLogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagocarvalho.activelife.firebaserepository.AuthRepository
import kotlinx.coroutines.launch
import okhttp3.internal.wait

class FlashCardLoginAndCreateAccViewModel() : ViewModel() {


    fun FirebaseSignInWithEmailAndPasswordViewModel(
        email: String,
        senha: String,
        home: () -> Unit,
        errors: (Exception?) -> Unit
    ) { viewModelScope.launch {  AuthRepository().FirebaseSignInWithEmailAndPassword(email, senha, home, errors)}}

    fun createUserWithEmailAndPassword(
        email: String,
        senha: String,
        home: () -> Unit,
        errors: (Exception?) -> Unit
    ) { viewModelScope.launch { AuthRepository().createUserWithEmailAndPassword(email, senha, home, errors)}}
}