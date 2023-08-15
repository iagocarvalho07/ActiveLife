package com.iagocarvalho.activelife.screens.workoutScreen

import androidx.lifecycle.ViewModel
import com.iagocarvalho.activelife.model.modelUsers.ModelExerciceFB
import com.iagocarvalho.activelife.network.ExercicioDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class WorkoutABCScreenViewModel: ViewModel() {

    val state = MutableStateFlow<MutableList<ModelExerciceFB>>(mutableListOf())
    private val dataStore =  ExercicioDbRepository()

    init {
        getExerciceFb()
    }

    fun getExerciceFb(): Flow<MutableList<ModelExerciceFB>> {
       return dataStore.getWorkOutFromFB()
    }
}