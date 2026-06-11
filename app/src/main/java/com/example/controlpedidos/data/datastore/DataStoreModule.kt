package com.example.controlpedidos.data.datastore

import android.content.Context
import com.example.controlpedidos.data.datastore.dataStore

object DataStoreModule {

    fun provideUserPreferencesRepository(
        context: Context
    ): UserPreferencesRepository {

        return UserPreferencesRepository(
            context.dataStore
        )
    }
}