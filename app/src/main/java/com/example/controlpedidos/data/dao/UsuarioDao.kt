package com.example.controlpedidos.data.dao

import androidx.room.*
import com.example.controlpedidos.data.entity.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {

    @Query("SELECT * FROM usuarios")
    fun getAll(): Flow<List<Usuario>>

    @Query("SELECT * FROM usuarios WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): Usuario?

    @Query("SELECT * FROM usuarios WHERE email = :email LIMIT 1")
    suspend fun getByEmail(email: String): Usuario?

    @Query(
        "SELECT * FROM usuarios WHERE email = :email AND senha = :senha LIMIT 1"
    )
    suspend fun login(
        email: String,
        senha: String
    ): Usuario?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(usuario: Usuario)

    @Update
    suspend fun update(usuario: Usuario)

    @Delete
    suspend fun delete(usuario: Usuario)
}