package com.example.controlpedidos.data.repository

import com.example.controlpedidos.data.dao.ProdutoDao
import com.example.controlpedidos.data.entity.Produto
import kotlinx.coroutines.flow.Flow

class ProdutoRepository(
    private val dao: ProdutoDao
) {
    fun getAll() = dao.getAll()

    suspend fun insert(produto: Produto) = dao.insert(produto)
    suspend fun update(produto: Produto) = dao.update(produto)
    suspend fun delete(produto: Produto) = dao.delete(produto)
}