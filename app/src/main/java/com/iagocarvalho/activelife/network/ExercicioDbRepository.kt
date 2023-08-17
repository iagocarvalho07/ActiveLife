package com.iagocarvalho.activelife.network

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.iagocarvalho.activelife.model.modelUsers.ModelExerciceFB
import com.iagocarvalho.activelife.network.modelApi.ExerciseDB
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ExercicioDbRepository {

    private val exerciseDbService = RetrofitInstance.ExerciseDB
    private val db = FirebaseFirestore.getInstance()
    private val currentUser = Firebase.auth.currentUser!!.uid

    private val _getExercices = MutableStateFlow<MutableList<ModelExerciceFB>>(mutableListOf())
    private val getAllExercicesFB: StateFlow<MutableList<ModelExerciceFB>> = _getExercices

    suspend fun getExerciciDb(): ExerciseDB {
        return exerciseDbService.getExerciseDb()

    }

    fun saveWorkout(
        bodyPart: String,
        equipment: String,
        gifUrl: String,
        id: String,
        name: String,
        target: String,
        treino: String,
        userId: String = currentUser
    ) {

        val exerciceMap = ModelExerciceFB(
            body_Part = bodyPart,
            equipment = equipment,
            gif_url = gifUrl,
            id = id,
            name = name,
            target = target,
            treino = treino,
            user_Id = userId

        ).toMap()
        db.collection(treino).add(exerciceMap)
            .addOnCompleteListener { task ->
                val docId = task.result.id
                Log.d("saveWorkoutDeucertoafasda", "saveWorkout: ${task.result.id}")
                    db.collection(treino).document(docId).update("documenteId", docId)
                        .addOnCompleteListener { tasks ->
                            Log.d(
                                "saveWorkoutDeucertoafasda",
                                "saveWorkout: ${tasks.result}"
                            )
                        }
                        .addOnFailureListener {
                            Log.d(
                                "FBD",
                                "saveToFireBase: Error updating doc",
                                it
                            )
                        }
                Log.d("saveWorkout", "saveWorkout: ${task.result.id}")
            }
            .addOnFailureListener { task -> Log.w("saveWorkout", "saveWorkout: ${task.message}") }


    }

    fun getWorkOutFromFB(): Flow<MutableList<ModelExerciceFB>> {
        val modelListExercice: MutableList<ModelExerciceFB> = mutableListOf()

        db.collection("treinoA").whereEqualTo("user_Id", currentUser).get()
            .addOnCompleteListener { quer ->
                if (quer.isSuccessful) {
                    for (documente in quer.result) {
                        Log.d("getWorkOutFromFB", "getWorkOutFromFB: ${documente}")
                        val exercice = documente.toObject(ModelExerciceFB::class.java)
                        modelListExercice.add(exercice)
                        _getExercices.value = modelListExercice
                    }
                }
            }
            .addOnFailureListener { task ->
                Log.d(
                    "getWorkOutFromFB",
                    "getWorkOutFromFB: ${task.message}"
                )
            }
        return getAllExercicesFB
    }

    fun updateWorkoutFromFb(
        treino: String,
        documenteId: String,
        repeticoes: String,
        series: String,
        cargar: String
    ) {
        db.collection(treino).document(documenteId)
            .update("cargar", cargar)
            .addOnCompleteListener { task ->
                Log.d(
                    "getWorkOutFromFB",
                    "updateWorkoutFromFb: ${task.result}"
                )
            }
            .addOnFailureListener { task ->
                Log.d(
                    "getWorkOutFromFB",
                    "updateWorkoutFromFb: ${task.message}"
                )
            }
    }
}