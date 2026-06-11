package com.example.controlpedidos.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pedidos")
data class Pedido(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val clienteId: Int,

    val produtoId: Int,

    val quantidade: Int,

    val valorTotal: Double,

    val data: String,

    val status: String
)