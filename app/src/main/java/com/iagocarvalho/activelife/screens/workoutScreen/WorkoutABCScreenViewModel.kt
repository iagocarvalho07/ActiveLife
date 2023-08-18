package com.iagocarvalho.activelife.screens.workoutScreen

import androidx.lifecycle.ViewModel
import com.iagocarvalho.activelife.model.modelUsers.ModelExerciceFB
import com.iagocarvalho.activelife.network.ExercicioDbRepository
import kotlinx.coroutines.flow.Flow

class WorkoutABCScreenViewModel : ViewModel() {
    private val dataStore = ExercicioDbRepository()

    init {
        getExerciceFb()
    }

    fun getExerciceFb(): Flow<MutableList<ModelExerciceFB>> {
        return dataStore.getWorkOutFromFB()
    }

    fun updateWorkOut(
        treino: String,
        campo: String,
        documenteId: String,
        repeticoes: String,
        series: String,
        cargar: String
    ) {
        return dataStore.updateWorkoutFromFb(treino, campo, documenteId, repeticoes, series, cargar)

    }
}