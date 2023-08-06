package com.iagocarvalho.activelife.screens.fullExerciseScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagocarvalho.activelife.network.ExercicioDbRepository
import com.iagocarvalho.activelife.network.modelApi.ExerciseDB
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FullExerciceScreenViewModel: ViewModel() {
    private val repository = ExercicioDbRepository()

    // primeira tentativa: Demora e atrazo na chamada da api -> correção utilizar stateflow
//    private val _ExerciceDBview = MutableLiveData<ExerciseDB>()
//    val ExerciseDBview: LiveData<ExerciseDB> = _ExerciceDBview

    private val _ExerciceDBview = MutableStateFlow(ExerciseDB())
    val ExerciseDBview: StateFlow<ExerciseDB> = _ExerciceDBview.asStateFlow()

    init {
        fetExerciceDbView()
    }

    private fun fetExerciceDbView(){
        viewModelScope.launch {
            try {
                val allExercises = repository.getExerciciDb()
                _ExerciceDBview.value =  allExercises
            }catch (e: Exception){
                Log.d("NETWORK API SERVICE", "fetExerciceDbView: ${e.message}")
            }
        }
    }
}