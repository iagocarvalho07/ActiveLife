package com.iagocarvalho.activelife.network.modelApi

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Exercises")
data class ExerciseDBItem(

    @ColumnInfo(name = "bodyPart")
    val bodyPart: String,

    @ColumnInfo(name = "equipment")
    val equipment: String,

    @ColumnInfo(name = "gifUrl")
    val gifUrl: String,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "target")
    val target: String
)