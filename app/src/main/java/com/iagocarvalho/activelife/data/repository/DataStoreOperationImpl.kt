package com.iagocarvalho.activelife.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.iagocarvalho.activelife.constants.Constants.PREFERENCES_KEY
import com.iagocarvalho.activelife.constants.Constants.PREFERENCES_NAME
import com.iagocarvalho.activelife.domain.repository.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class DataStoreOperationImpl(context: Context) : DataStoreOperations {

    private object PreferencesKey {
        val onBordingKey = booleanPreferencesKey(name = PREFERENCES_KEY)
    }

    private val dataStore = context.dataStore
    override suspend fun saveOnBordingState(completed: Boolean) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[PreferencesKey.onBordingKey] = completed

        }
    }

    override fun readOnBorardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exepetion ->
                if (exepetion is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exepetion
                }
            }.map { preferences ->
                val onBordingState = preferences[PreferencesKey.onBordingKey] ?: false
                onBordingState
            }
    }
}