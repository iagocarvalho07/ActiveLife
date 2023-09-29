package com.iagocarvalho.activelife.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iagocarvalho.activelife.constants.Constants.EXERCISE_REMOTE_KEY_TABLE

@Entity(tableName = EXERCISE_REMOTE_KEY_TABLE)
data class ExerciseRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevKey: Int?,
    val nextKey: Int?,
)
