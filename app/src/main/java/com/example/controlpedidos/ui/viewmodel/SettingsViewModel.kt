package com.example.controlpedidos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlpedidos.data.datastore.DataStoreManager
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val dataStore: DataStoreManager
) : ViewModel() {

    val userPreferences = dataStore.userPreferencesFlow

    fun alterarTema(enabled: Boolean) {
        viewModelScope.launch {
            dataStore.setDarkTheme(enabled)
        }
    }

    fun logout() {
        viewModelScope.launch {
            dataStore.logout()
        }
    }
}