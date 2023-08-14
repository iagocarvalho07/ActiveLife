package com.iagocarvalho.activelife.network.roomDataBaseCache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iagocarvalho.activelife.network.modelApi.ExerciseDBItem

@Database(entities = [ExerciseDBItem::class], version = 1, exportSchema = false)
abstract class ExerciceDataBase : RoomDatabase() {
    abstract fun ExerciceDao(): ExerciceDao

    companion object {
        private var INSTANCE: ExerciceDataBase? = null
        fun getInstance(context: Context): ExerciceDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ExerciceDataBase::class.java,
                        "Exercise_list_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}