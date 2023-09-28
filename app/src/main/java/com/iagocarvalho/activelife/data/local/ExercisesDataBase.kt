package com.iagocarvalho.activelife.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iagocarvalho.activelife.data.local.dao.ExercicesDao
import com.iagocarvalho.activelife.domain.model.Exercices

@Database(entities = [Exercices::class], version = 1)
abstract class ExercisesDataBase : RoomDatabase() {

    abstract fun ExercisesDao(): ExercicesDao
}