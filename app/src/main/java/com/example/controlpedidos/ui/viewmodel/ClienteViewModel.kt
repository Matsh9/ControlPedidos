package com.example.controlpedidos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlpedidos.data.repository.ClienteRepository
import com.example.controlpedidos.data.entity.Cliente
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ClienteViewModel(
    private val clienteRepository: ClienteRepository
) : ViewModel() {

    val clientes = clienteRepository.getAll()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun adicionarCliente(cliente: Cliente) {
        viewModelScope.launch {
            clienteRepository.insert(cliente)
        }
    }

    fun atualizarCliente(cliente: Cliente) {
        viewModelScope.launch {
            clienteRepository.update(cliente)
        }
    }

    fun removerCliente(cliente: Cliente) {
        viewModelScope.launch {
            clienteRepository.delete(cliente)
        }
    }
}