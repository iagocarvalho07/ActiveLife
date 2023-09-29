package com.iagocarvalho.activelife.di

import android.content.Context
import androidx.room.Room
import com.iagocarvalho.activelife.constants.Constants.EXERCICE_DATABASE
import com.iagocarvalho.activelife.data.local.ExercisesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {


    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ExercisesDataBase::class.java, EXERCICE_DATABASE).build()


}