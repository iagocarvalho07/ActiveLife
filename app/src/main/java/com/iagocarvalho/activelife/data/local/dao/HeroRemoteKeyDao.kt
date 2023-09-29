package com.iagocarvalho.activelife.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iagocarvalho.activelife.domain.model.ExerciseRemoteKey

@Dao
interface HeroRemoteKeyDao {

    @Query("SELECT * FROM exercise_Remote_key_table WHERE id = :id")
    suspend fun getRemoteKey(id: Int): ExerciseRemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(exerciseRemoteKey: List<ExerciseRemoteKey>)

    @Query("DELETE  FROM exercise_Remote_key_table")
    suspend fun deleteAllRemoteKeys()
}