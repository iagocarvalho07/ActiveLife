package com.iagocarvalho.activelife.firebaserepository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.iagocarvalho.activelife.model.modelUsers.ModelUser

class AuthRepository {

    private var authRepository: FirebaseAuth = Firebase.auth
    private var currentUser = Firebase.auth.currentUser


    private val _loading = MutableLiveData(false)

    fun firebaseSignInWithEmailAndPassword(
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
                        home()
                    } else {
                        Log.w("EXC", "firebaseSignInWithEmailAndPassword: ${task.exception}")
                        errors(task.exception)
                    }
                    _loading.value = false
                }
        }
    }

    fun createUserWithEmailAndPassword(
        name: String,
        peso: String,
        altura: String,
        idade: String,
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
                        creatUser(
                            name = name,
                            peso = peso,
                            altura = altura,
                            idade = idade,
                            email = email,
                            avatarURL = ""
                        )
                        home()


                    } else {
                        Log.w("EXC", "createUserWithEmailAndPassword: Deu Merda${task.exception}")
                        errors(task.exception)

                    }
                }
        }
    }

    fun sendEmailVerification() {
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

    fun singOut() {
        authRepository.signOut()
    }

    private fun creatUser(
        name: String,
        peso: String,
        altura: String,
        idade: String,
        email: String,
        avatarURL: String
    ) {
        val userId = authRepository.currentUser?.uid
        val user = ModelUser(
            user_id = userId.toString(),
            display_name = name,
            peso = peso,
            altura = altura,
            idade = idade,
            email = email,
            avatarURL = avatarURL
        ).toMap()
        FirebaseFirestore.getInstance().collection("users")
            .add(user)
            .addOnCompleteListener { task ->
                val docId = task.result.id
                Log.d("updateUser", "CreatUser: Document $docId")
                FirebaseFirestore.getInstance().collection("users").document(docId)
                    .update("documenteId", docId)
                    .addOnCompleteListener { tasks ->
                        Log.d(
                            "updateUser",
                            "CreatUser: ${tasks.result}"
                        )
                    }
                    .addOnFailureListener { tasks ->
                        Log.d(
                            "updateUser",
                            "CreatUser: ${tasks.message}"
                        )
                    }

            }
            .addOnFailureListener { e ->
                Log.d(
                    "updateUser",
                    "CreatUser: documento n foi criado $e"
                )
            }
    }

    fun deleteUser() {
        currentUser!!.delete()
            .addOnCompleteListener { task ->
            }
    }
}


