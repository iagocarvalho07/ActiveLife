package com.iagocarvalho.activelife.network

import com.iagocarvalho.activelife.network.modelApi.ExerciseDB
import retrofit2.http.GET

interface ExerciseDBService  {
    @GET("exercises")
    suspend fun getExerciseDb(): ExerciseDB
}