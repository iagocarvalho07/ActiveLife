package com.iagocarvalho.activelife.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iagocarvalho.activelife.domain.model.Exercices

@Dao
interface ExercicesDao {

    @Query("SELECT * FROM Exercicos_Table ORDER by id ASC")
    fun getAllHeros(): PagingSource<Int, Exercices>

    @Query("SELECT *FROM Exercicos_Table WHERE id=:exerciseId")
    fun getSelectedExercises(exerciseId: Int): Exercices

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addExercises(exercises: Exercices)

    @Query("DELETE FROM Exercicos_Table")
    suspend fun deleteAllExercises()
}