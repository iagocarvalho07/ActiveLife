package com.iagocarvalho.activelife.screens.fullExerciseScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagocarvalho.activelife.network.ExercicioDbRepository
import com.iagocarvalho.activelife.network.modelApi.ExerciseDB
import kotlinx.coroutines.launch

class FullExerciceScreenViewModel: ViewModel() {
    private val repository = ExercicioDbRepository()

    private val _ExerciceDBview = MutableLiveData<ExerciseDB>()
    val ExerciseDBview: LiveData<ExerciseDB> = _ExerciceDBview

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