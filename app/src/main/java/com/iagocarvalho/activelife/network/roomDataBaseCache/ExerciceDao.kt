package com.iagocarvalho.activelife.network.roomDataBaseCache

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.iagocarvalho.activelife.network.modelApi.ExerciseDBItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExercices(employee: ExerciseDBItem)

    @Query("SELECT * FROM Exercises")
    fun getAllEmployees(): Flow<List<ExerciseDBItem>>

    @Update
    suspend fun updateEmployeeDetails(employee: ExerciseDBItem)

    @Delete
    suspend fun deleteEmployee(employee: ExerciseDBItem)
}