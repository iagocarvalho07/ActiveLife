package com.iagocarvalho.activelife.network

import com.iagocarvalho.activelife.network.modelApi.ExerciseDB

class ExercicioDbRepository {

    private val ExerciseDbService = RetrofitInstance.ExerciseDB

    suspend fun getExerciciDb(): ExerciseDB{
        return ExerciseDbService.getExerciseDb()
    }

}