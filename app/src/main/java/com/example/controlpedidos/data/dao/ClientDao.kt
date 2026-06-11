package com.example.controlpedidos.data.dao

import androidx.room.*
import com.example.controlpedidos.data.entity.Cliente
import kotlinx.coroutines.flow.Flow

@Dao
interface ClienteDao {

    @Query("SELECT * FROM clientes")
    fun getAll(): Flow<List<Cliente>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cliente: Cliente)

    @Update
    suspend fun update(cliente: Cliente)

    @Delete
    suspend fun delete(cliente: Cliente)
}