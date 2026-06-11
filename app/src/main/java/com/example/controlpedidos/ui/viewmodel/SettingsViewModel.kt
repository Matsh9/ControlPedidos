package com.example.controlpedidos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlpedidos.data.datastore.UserPreferences
import com.example.controlpedidos.data.datastore.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repository: UserPreferencesRepository
) : ViewModel() {

    val userPreferences: Flow<UserPreferences> =
        repository.userPreferences

    fun alterarTema(
        darkTheme: Boolean
    ) {

        viewModelScope.launch {

            repository.saveTheme(
                darkTheme
            )
        }
    }

    fun logout() {

        viewModelScope.launch {

            repository.logout()
        }
    }
}