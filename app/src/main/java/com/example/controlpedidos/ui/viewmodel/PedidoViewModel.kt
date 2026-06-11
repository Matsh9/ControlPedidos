package com.example.controlpedidos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlpedidos.data.entity.Pedido
import com.example.controlpedidos.data.repository.PedidoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PedidoViewModel(
    private val repository: PedidoRepository
) : ViewModel() {

    private val _pedidos =
        MutableStateFlow<List<Pedido>>(emptyList())

    val pedidos: StateFlow<List<Pedido>> =
        _pedidos.asStateFlow()

    init {
        carregarPedidos()
    }

    private fun carregarPedidos() {
        viewModelScope.launch {
            repository.getAllPedidos().collect {
                _pedidos.value = it
            }
        }
    }

    fun adicionarPedido(
        pedido: Pedido
    ) {
        viewModelScope.launch {
            repository.insert(pedido)
        }
    }

    fun atualizarPedido(
        pedido: Pedido
    ) {
        viewModelScope.launch {
            repository.update(pedido)
        }
    }

    fun removerPedido(
        pedido: Pedido
    ) {
        viewModelScope.launch {
            repository.delete(pedido)
        }
    }
}