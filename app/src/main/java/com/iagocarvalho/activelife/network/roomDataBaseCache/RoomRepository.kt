package com.iagocarvalho.activelife.network.roomDataBaseCache

import com.iagocarvalho.activelife.network.modelApi.ExerciseDBItem
import kotlinx.coroutines.flow.Flow

typealias Exercises = List<ExerciseDBItem>
interface RoomRepository {

    fun  getExercicesFromRoom () : Flow<Exercises>

    suspend  fun  getExercicesFromRoom (id: Int ) : ExerciseDBItem

    suspend  fun  addExercicesToRoom (Exercises: ExerciseDBItem )

    suspend fun getExercisesByBodyPart(bodyPart: String): ExerciseDBItem

    suspend  fun  updateExercicesInRoom (Exercises: ExerciseDBItem )

    suspend  fun  deleteExercicesToromRoom (Exercises : ExerciseDBItem )
}