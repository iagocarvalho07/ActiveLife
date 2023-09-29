package com.iagocarvalho.activelife.data.remote

import com.iagocarvalho.activelife.domain.model.modelUsers.ExerciceDB
import retrofit2.http.GET

interface ExercisesApi {
    @GET("exercises")
    suspend fun getExerciseDb(): ExerciceDB
}