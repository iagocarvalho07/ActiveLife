package com.iagocarvalho.activelife.screens.fullExerciseScreen

import android.app.Application
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.iagocarvalho.activelife.network.ExercicioDbRepository
import com.iagocarvalho.activelife.network.modelApi.ExerciseDB
import com.iagocarvalho.activelife.network.roomDataBaseCache.ExerciceDataBase
import com.iagocarvalho.activelife.network.roomDataBaseCache.ExerciceRoomRepositoryIMPL
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch


class FullExerciceScreenViewModel(
    application: Application, ) : AndroidViewModel(application) {
    private val repository = ExercicioDbRepository()
    val repositorys = ExerciceRoomRepositoryIMPL(
        ExerciceDataBase.getInstance(application.applicationContext)
            .ExerciceDao()
    )
    //private val _ExerciceDBview = MutableStateFlow(ExerciseDB())
    //val ExerciseDBview: StateFlow<ExerciseDB> = _ExerciceDBview.asStateFlow()
    init {
        fetExerciceDbView()

    }

    val banco = repositorys.getExercicesFromRoom()

    private fun fetExerciceDbView() {
        viewModelScope.launch {
            try {
                val allExercises = repository.getExerciciDb()
                if (banco.toList().isEmpty()){
                    Log.d("Rooo", "fetExerciceDbView: ${banco.toList()} ")
                    for (exercices in allExercises) {
                        Log.d("RoomTeste", "fetExerciceDbView: $exercices")
                        repositorys.addExercicesToRoom(exercices)
                    }

                }

            } catch (e: Exception) {
                Log.d("NETWORK API SERVICE", "fetExerciceDbView: ${e.message}")
            }
        }
    }
}