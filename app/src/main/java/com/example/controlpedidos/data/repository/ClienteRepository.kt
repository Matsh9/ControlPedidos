package com.example.controlpedidos.data.repository

import com.example.controlpedidos.data.dao.ClienteDao
import com.example.controlpedidos.data.entity.Cliente
import kotlinx.coroutines.flow.Flow

class ClienteRepository(
    private val dao: ClienteDao
) {

    fun getAll(): Flow<List<Cliente>> = dao.getAll()

    suspend fun insert(cliente: Cliente) = dao.insert(cliente)

    suspend fun update(cliente: Cliente) = dao.update(cliente)

    suspend fun delete(cliente: Cliente) = dao.delete(cliente)
}