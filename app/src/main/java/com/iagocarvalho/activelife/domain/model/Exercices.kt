package com.iagocarvalho.activelife.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iagocarvalho.activelife.constants.Constants.HERO_DATA_BASE_TABLE

@Entity(tableName = HERO_DATA_BASE_TABLE)
data class Exercices(
    val bodyPart: String,
    val equipment: String,
    val gifUrl: String,
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val target: String
)
