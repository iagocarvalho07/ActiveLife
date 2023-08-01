package com.iagocarvalho.activelife.firebaserepository

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.ktx.Firebase
import com.iagocarvalho.activelife.model.modelUsers.ModelUser
import kotlinx.coroutines.tasks.await

class DataSorce {
    suspend fun getDataUsersFromFireStore(): ModelUser {
        val db = FirebaseFirestore.getInstance()
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

}