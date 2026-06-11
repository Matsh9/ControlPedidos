package com.example.controlpedidos.data.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferencesRepository(
    private val dataStore: androidx.datastore.core.DataStore<Preferences>
) {

    companion object {

        private val IS_LOGGED_IN =
            booleanPreferencesKey("is_logged_in")

        private val USER_ID =
            intPreferencesKey("user_id")

        private val USER_NAME =
            stringPreferencesKey("user_name")

        private val USER_EMAIL =
            stringPreferencesKey("user_email")

        private val DARK_THEME =
            booleanPreferencesKey("dark_theme")
    }

    val userPreferences: Flow<UserPreferences> =
        dataStore.data.map { preferences ->

            UserPreferences(

                isLoggedIn =
                    preferences[IS_LOGGED_IN] ?: false,

                userId =
                    preferences[USER_ID] ?: 0,

                userName =
                    preferences[USER_NAME] ?: "",

                userEmail =
                    preferences[USER_EMAIL] ?: "",

                darkTheme =
                    preferences[DARK_THEME] ?: false
            )
        }

    suspend fun saveLogin(
        userId: Int,
        userName: String,
        userEmail: String
    ) {

        dataStore.edit { preferences ->

            preferences[IS_LOGGED_IN] = true
            preferences[USER_ID] = userId
            preferences[USER_NAME] = userName
            preferences[USER_EMAIL] = userEmail
        }
    }

    suspend fun saveTheme(
        darkTheme: Boolean
    ) {

        dataStore.edit { preferences ->

            preferences[DARK_THEME] =
                darkTheme
        }
    }

    suspend fun logout() {

        dataStore.edit { preferences ->

            preferences[IS_LOGGED_IN] = false
            preferences[USER_ID] = 0
            preferences[USER_NAME] = ""
            preferences[USER_EMAIL] = ""
        }
    }
}