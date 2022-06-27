package udemy.boruto.compose.myapplication.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import udemy.boruto.compose.myapplication.domain.repository.DataStoreOperations
import udemy.boruto.compose.myapplication.utils.Constraint.PREFERENCE_KEY
import udemy.boruto.compose.myapplication.utils.Constraint.PREFERENCE_NAME
import java.io.IOException

val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

class DataStoreOperationsImpl(context : Context) : DataStoreOperations {

    private object PreferencesKey{
        val onBoardingKey = booleanPreferencesKey(name = PREFERENCE_KEY)
    }

    private val dataStore = context.dataStore

    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data.catch { exception ->
            if (exception is IOException){
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
            .map { preferences ->
                val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
                onBoardingState
            }
    }
}