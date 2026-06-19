package com.example.controlpedidos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import com.example.controlpedidos.data.datastore.DataStoreManager
import com.example.controlpedidos.data.entity.Usuario
import com.example.controlpedidos.data.repository.UsuarioRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val usuarioRepository: UsuarioRepository,
    private val dataStore: DataStoreManager
) : ViewModel() {

    var errorMessage = mutableStateOf<String?>(null)
        private set

    fun login(email: String, senha: String, onSuccess: () -> Unit) {
        viewModelScope.launch {

            val usuario = usuarioRepository.login(email, senha)

            if (usuario != null) {

                dataStore.saveLogin(
                    id = usuario.id,
                    name = usuario.nome,
                    email = usuario.email
                )

                errorMessage.value = null
                onSuccess()

            } else {
                errorMessage.value = "Email ou senha inválidos"
            }
        }
    }

    fun registrar(
        nome: String,
        email: String,
        senha: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {

            val usuarioExistente = usuarioRepository.getByEmail(email)

            if (usuarioExistente != null) {
                onError("E-mail já cadastrado")
                return@launch
            }

            usuarioRepository.insert(
                Usuario(
                    nome = nome,
                    email = email,
                    senha = senha
                )
            )

            onSuccess()
        }
    }
}