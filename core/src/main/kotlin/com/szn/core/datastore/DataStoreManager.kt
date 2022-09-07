package com.szn.core.datastore
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

const val LAST_UPDATE = "LastUpdate"

/**
 * Helper class for write & read values in preferences storage
 */
class DataStoreManager(val context: Context) {

    suspend fun getValueByKey(key: Preferences.Key<*>): Any? {
        val value = context.dataStore.data
            .map {
                it[key]
            }
        return value.firstOrNull()
    }

    suspend fun add(key: String, value: Any) {
        context.dataStore.edit { settings ->
            settings[stringPreferencesKey(key)] = value.toString()
        }
    }

    suspend fun remove(key: String) {
        context.dataStore.edit { settings ->
            settings.remove(stringPreferencesKey(key))
        }
    }

    suspend fun getValue(key: String): Any? {
        val value = context.dataStore.data
            .map {
                it[stringPreferencesKey(key)]
            }
        return value.firstOrNull()
    }

    suspend fun get(key: String): String? {
        val value = context.dataStore.data
            .map {
                it[stringPreferencesKey(key)]
            }
        return value.firstOrNull()
    }

    suspend fun clear(){
        context.dataStore.edit {
            it.clear()
        }
    }

    suspend fun lastUpdated(): Long {
        val up = getValue(LAST_UPDATE)
        return up?.toString()?.toLong()?: 0
    }

    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    }
}