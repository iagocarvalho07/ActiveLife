package com.iagocarvalho.activelife.screens.loginScreens.EmailLogin

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FlashCardLoginAndCreateAccViewModel: ViewModel() {
    private var auth: FirebaseAuth = Firebase.auth
    private var CurrentUser = Firebase.auth.currentUser
}