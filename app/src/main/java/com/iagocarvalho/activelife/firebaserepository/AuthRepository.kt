package com.iagocarvalho.activelife.firebaserepository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthRepository {

    private var authRepository: FirebaseAuth = Firebase.auth
    private var currentUser = Firebase.auth.currentUser


    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun FirebaseSignInWithEmailAndPassword(
        email: String,
        password: String,
        home: () -> Unit,
        errors: (Exception?) -> Unit
    ) {
        if (_loading.value == false) {
            _loading.value = true
            authRepository.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Login", "firebaseSignInWithEmailAndPassword: ${task.result}")
                        val user = authRepository.currentUser
                        home()
                        updateUi(user)
                    } else {
                        Log.w("EXC", "firebaseSignInWithEmailAndPassword: ${task.exception}")
                        errors(task.exception)
                    }
                    _loading.value = false
                }
        }
    }

    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        home: () -> Unit,
        errors: (Exception?) -> Unit
    ) {
        if (_loading.value == false) {
            _loading.value = true
            authRepository.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(
                            "createUser",
                            "createUserWithEmailAndPassword Deu certo: ${task.isSuccessful} "
                        )
                        home()
                        val user = authRepository.currentUser
                        updateUi(user)
                    } else {
                        Log.w("EXC", "createUserWithEmailAndPassword: Deu Merda${task.exception}")
                        errors(task.exception)

                    }
                }
        }
    }

    fun SendEmailVerification() {
        currentUser!!.sendEmailVerification()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("sendEmail", "sendEmailVerification: email enviado ")
                } else {
                    Log.d("FB", "sendEmailVerification: ${task.exception}")
                }
            }
    }

    fun sendPasswordResetEmail(
        email: String,
        home: () -> Unit,
        errors: (Exception?) -> Unit
    ) {
        Firebase.auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(
                        "sendpassword",
                        "sendPasswordResetEmail: email Redefinição de  senha enviado"
                    )
                    home()
                } else {
                    Log.w("error", "sendPasswordResetEmail: ${task.exception}")
                    errors(task.exception)
                }
            }
    }

    fun SingOut() {
        authRepository.signOut()
    }

    private fun updateUi(user: FirebaseUser?) {
    }
}

