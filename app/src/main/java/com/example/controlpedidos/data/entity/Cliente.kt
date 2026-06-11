package com.example.controlpedidos.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clientes")
data class Cliente(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nome: String,

    val email: String,

    val telefone: String,

    val endereco: String
)