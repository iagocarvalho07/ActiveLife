package com.iagocarvalho.activelife.network.roomDataBaseCache

import com.iagocarvalho.activelife.network.modelApi.ExerciseDBItem
import kotlinx.coroutines.flow.Flow

class ExerciceRoomRepositoryIMPL(private val ExerciceDao: ExerciceDao) : RoomRepository {
    override fun getExercicesFromRoom(): Flow<Exercises> = ExerciceDao.getAllExercises()

    override suspend fun getExercicesFromRoom(id: Int) = ExerciceDao.getExercises(id)


    override suspend fun addExercicesToRoom(Exercises: ExerciseDBItem) =
        ExerciceDao.addExercices(Exercises)

    override suspend fun updateExercicesInRoom(Exercises: ExerciseDBItem) =
        ExerciceDao.updateExerciesDetails(Exercises)

    override suspend fun deleteExercicesToromRoom(Exercises: ExerciseDBItem) =
        ExerciceDao.deleteEmployee(Exercises)
}