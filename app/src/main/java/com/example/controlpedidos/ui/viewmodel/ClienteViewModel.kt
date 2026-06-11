package com.example.controlpedidos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlpedidos.data.entity.Cliente
import com.example.controlpedidos.data.repository.ClienteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ClienteViewModel(
    private val repository: ClienteRepository
) : ViewModel() {

    private val _clientes =
        MutableStateFlow<List<Cliente>>(emptyList())

    val clientes: StateFlow<List<Cliente>> =
        _clientes.asStateFlow()

    init {
        carregarClientes()
    }

    private fun carregarClientes() {
        viewModelScope.launch {
            repository.getAllClientes().collect {
                _clientes.value = it
            }
        }
    }

    fun adicionarCliente(
        cliente: Cliente
    ) {
        viewModelScope.launch {
            repository.insert(cliente)
        }
    }

    fun atualizarCliente(
        cliente: Cliente
    ) {
        viewModelScope.launch {
            repository.update(cliente)
        }
    }

    fun removerCliente(
        cliente: Cliente
    ) {
        viewModelScope.launch {
            repository.delete(cliente)
        }
    }
}