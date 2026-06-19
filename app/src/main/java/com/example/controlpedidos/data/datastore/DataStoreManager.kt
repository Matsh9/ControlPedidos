package com.example.controlpedidos.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user_preferences")

data class UserPreferences(
    val isLoggedIn: Boolean = false,
    val userId: Int = 0,
    val userName: String = "",
    val userEmail: String = "",
    val darkTheme: Boolean = false
)

class DataStoreManager(private val context: Context) {

    companion object {
        val USER_ID = intPreferencesKey("user_id")
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_EMAIL = stringPreferencesKey("user_email")
        val DARK_THEME = booleanPreferencesKey("dark_theme")
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }

    val userPreferencesFlow: Flow<UserPreferences> =
        context.dataStore.data.map { prefs ->
            UserPreferences(
                isLoggedIn = prefs[IS_LOGGED_IN] ?: false,
                userId = prefs[USER_ID] ?: 0,
                userName = prefs[USER_NAME] ?: "",
                userEmail = prefs[USER_EMAIL] ?: "",
                darkTheme = prefs[DARK_THEME] ?: false
            )
        }

    suspend fun saveLogin(id: Int, name: String, email: String) {
        context.dataStore.edit {
            it[USER_ID] = id
            it[USER_NAME] = name
            it[USER_EMAIL] = email
            it[IS_LOGGED_IN] = true
        }
    }

    suspend fun setDarkTheme(enabled: Boolean) {
        context.dataStore.edit {
            it[DARK_THEME] = enabled
        }
    }

    suspend fun logout() {
        context.dataStore.edit {
            it.clear()
        }
    }
}