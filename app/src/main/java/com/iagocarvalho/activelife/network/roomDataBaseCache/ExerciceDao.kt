package com.iagocarvalho.activelife.network.roomDataBaseCache

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.iagocarvalho.activelife.network.modelApi.ExerciseDB
import com.iagocarvalho.activelife.network.modelApi.ExerciseDBItem

@Dao
interface ExerciceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExercices(employee: ExerciseDB)

    @Query("SELECT * FROM Exercises")
    fun getAllEmployees(): LiveData<List<ExerciseDB>>

    @Update
    suspend fun updateEmployeeDetails(employee: ExerciseDBItem)

    @Delete
    suspend fun deleteEmployee(employee: ExerciseDBItem)
}