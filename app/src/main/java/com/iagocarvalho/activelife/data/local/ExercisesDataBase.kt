package com.iagocarvalho.activelife.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iagocarvalho.activelife.data.local.dao.ExercicesDao
import com.iagocarvalho.activelife.data.local.dao.HeroRemoteKeyDao
import com.iagocarvalho.activelife.domain.model.Exercices
import com.iagocarvalho.activelife.domain.model.ExerciseRemoteKey

@Database(entities = [Exercices::class, ExerciseRemoteKey::class], version = 1)
abstract class ExercisesDataBase : RoomDatabase() {

    abstract fun exercisesDao(): ExercicesDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao
}