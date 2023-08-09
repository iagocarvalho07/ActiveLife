package com.iagocarvalho.activelife.network.roomDataBaseCache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iagocarvalho.activelife.network.modelApi.ExerciseDB

@Database(entities = [ExerciseDB::class], version = 1)
abstract class ExerciceDataBase : RoomDatabase() {
    abstract fun exercice(): ExerciceDao

    companion object {
        private const val DATABASE_NAME: String = "EXERCICE_BANCO"

        @Volatile
        private var INSTANCE: ExerciceDataBase? = null

        fun getInsance(context: Context): ExerciceDataBase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDataBase(context).also { INSTANCE = it }
        }

        private fun buildDataBase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ExerciceDataBase::class.java,
            DATABASE_NAME
        ).build()
    }
}