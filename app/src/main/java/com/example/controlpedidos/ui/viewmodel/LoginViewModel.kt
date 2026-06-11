package com.example.controlpedidos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlpedidos.data.datastore.UserPreferencesRepository
import com.example.controlpedidos.data.repository.UsuarioRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val usuarioRepository: UsuarioRepository,
    private val preferencesRepository: UserPreferencesRepository
) : ViewModel() {

    fun login(
        email: String,
        senha: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {

        viewModelScope.launch {

            val usuario =
                usuarioRepository.login(
                    email,
                    senha
                )

            if (usuario != null) {

                preferencesRepository.saveLogin(
                    userId = usuario.id,
                    userName = usuario.nome,
                    userEmail = usuario.email
                )

                onSuccess()

            } else {

                onError()
            }
        }
    }
}