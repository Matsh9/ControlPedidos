package com.example.controlpedidos.data.datastore

data class UserPreferences(

    val isLoggedIn: Boolean = false,

    val userId: Int = 0,

    val userName: String = "",

    val userEmail: String = "",

    val darkTheme: Boolean = false
)