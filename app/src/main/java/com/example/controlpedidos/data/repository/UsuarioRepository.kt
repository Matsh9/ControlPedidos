package com.example.controlpedidos.data.repository

import com.example.controlpedidos.data.dao.UsuarioDao
import com.example.controlpedidos.data.entity.Usuario
import kotlinx.coroutines.flow.Flow

class UsuarioRepository(
    private val dao: UsuarioDao
) {

    fun getAllUsuarios(): Flow<List<Usuario>> {
        return dao.getAll()
    }

    suspend fun getById(
        id: Int
    ): Usuario? {
        return dao.getById(id)
    }

    suspend fun getByEmail(
        email: String
    ): Usuario? {
        return dao.getByEmail(email)
    }

    suspend fun login(
        email: String,
        senha: String
    ): Usuario? {
        return dao.login(email, senha)
    }

    suspend fun insert(
        usuario: Usuario
    ) {
        dao.insert(usuario)
    }

    suspend fun update(
        usuario: Usuario
    ) {
        dao.update(usuario)
    }

    suspend fun delete(
        usuario: Usuario
    ) {
        dao.delete(usuario)
    }
}