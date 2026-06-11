package com.example.controlpedidos.data.repository

import com.example.controlpedidos.data.dao.PedidoDao
import com.example.controlpedidos.data.entity.Pedido
import kotlinx.coroutines.flow.Flow

class PedidoRepository(
    private val dao: PedidoDao
) {

    fun getAllPedidos(): Flow<List<Pedido>> {
        return dao.getAll()
    }

    suspend fun insert(
        pedido: Pedido
    ) {
        dao.insert(pedido)
    }

    suspend fun update(
        pedido: Pedido
    ) {
        dao.update(pedido)
    }

    suspend fun delete(
        pedido: Pedido
    ) {
        dao.delete(pedido)
    }
}