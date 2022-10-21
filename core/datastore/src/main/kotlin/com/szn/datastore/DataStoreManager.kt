package com.szn.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

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

    suspend fun setLastUpdated(updated: Long) {
        add(LAST_UPDATE, updated)
    }

    suspend fun lastUpdated(): Long {
        val up = getValue(LAST_UPDATE)
        return up?.toString()?.toLong()?: 0
    }

    suspend fun getToken(): String? {
        return get(TOKEN)
    }

    suspend fun setToken(token: String){
        add(TOKEN, token)
    }
    suspend fun getSessionId(): String? {
        return get(SESS_ID)
    }

    suspend fun setSessionId(token: String){
        add(SESS_ID, token)
    }

    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
        const val TOKEN = "token"
        const val SESS_ID = "token"
        const val LAST_UPDATE = "LastUpdate"
        const val ACCOUNT_ID = "account_id"
        const val ACCOUNT = "account"
    }
}