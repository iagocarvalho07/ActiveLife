package com.iagocarvalho.activelife.firebaserepository

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.ktx.Firebase
import com.iagocarvalho.activelife.model.modelUsers.ModelUser
import kotlinx.coroutines.tasks.await

class DataSorce {
    val db = FirebaseFirestore.getInstance()

    suspend fun getDataUsersFromFireStore(): ModelUser {
        var modelUser = ModelUser()
        val currentuser = Firebase.auth.currentUser

        try {
            db.collection("users").whereEqualTo("user_id", currentuser!!.uid).get().await().map {
                val result = it.toObject(ModelUser::class.java)
                modelUser = result
            }
        } catch (e: FirebaseFirestoreException) {
            Log.d("Errorview", "getDataFromFireStore: $e")
        }
        return modelUser
    }

    fun updateUserFromFb(
        documenteId: String,
        campo: String,
        valor: String,

    ){
        db.collection("users").document(documenteId)
            .update(campo, valor)
            .addOnCompleteListener{task -> Log.d("UpdateUser", "updateUserFromFb: ${task.result}")}
            .addOnFailureListener { task -> Log.d("UpdateUser", "updateUserFromFb: ${task.message}") }
    }
}

