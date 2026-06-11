package com.example.controlpedidos.data.dao

import androidx.room.*
import com.example.controlpedidos.data.entity.Pedido
import kotlinx.coroutines.flow.Flow

@Dao
interface PedidoDao {

    @Query("SELECT * FROM pedidos")
    fun getAll(): Flow<List<Pedido>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pedido: Pedido)

    @Update
    suspend fun update(pedido: Pedido)

    @Delete
    suspend fun delete(pedido: Pedido)
}