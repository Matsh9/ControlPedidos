package com.example.controlpedidos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlpedidos.data.repository.ClienteRepository
import com.example.controlpedidos.data.repository.PedidoRepository
import com.example.controlpedidos.data.repository.ProdutoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeViewModel(
    private val clienteRepository: ClienteRepository,
    private val produtoRepository: ProdutoRepository,
    private val pedidoRepository: PedidoRepository
) : ViewModel() {

    private val _totalClientes = MutableStateFlow(0)
    val totalClientes: StateFlow<Int> = _totalClientes

    private val _totalProdutos = MutableStateFlow(0)
    val totalProdutos: StateFlow<Int> = _totalProdutos

    private val _totalPedidos = MutableStateFlow(0)
    val totalPedidos: StateFlow<Int> = _totalPedidos

    fun carregar() {
        viewModelScope.launch {
            _totalClientes.value = clienteRepository.getAll().first().size
            _totalProdutos.value = produtoRepository.getAll().first().size
            _totalPedidos.value = pedidoRepository.getAll().first().size
        }
    }
}