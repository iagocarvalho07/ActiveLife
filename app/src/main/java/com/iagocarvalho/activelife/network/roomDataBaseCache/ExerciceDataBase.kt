package com.iagocarvalho.activelife.network.roomDataBaseCache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iagocarvalho.activelife.network.modelApi.ExerciseDBItem

@Database(entities = [ExerciseDBItem::class], version = 1, exportSchema = false)
abstract class ExerciceDataBase : RoomDatabase() {
    abstract fun exercice(): ExerciceDao

    companion object {
        private const val DATABASE_NAME: String = "EXERCICE_BANCO"

        @Volatile
        private var INSTANCE: ExerciceDataBase? = null

        fun getInsance(context: Context): ExerciceDataBase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDataBase(context).also { INSTANCE = it }
            }
        }

        private fun buildDataBase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ExerciceDataBase::class.java,
            DATABASE_NAME
        ).build()
    }
}