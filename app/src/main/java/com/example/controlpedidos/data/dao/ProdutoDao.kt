package com.example.controlpedidos.data.dao

import androidx.room.*
import com.example.controlpedidos.data.entity.Produto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM produtos")
    fun getAll(): Flow<List<Produto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(produto: Produto)

    @Update
    suspend fun update(produto: Produto)

    @Delete
    suspend fun delete(produto: Produto)
}