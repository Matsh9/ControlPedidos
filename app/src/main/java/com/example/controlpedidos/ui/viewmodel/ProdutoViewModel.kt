package com.example.controlpedidos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlpedidos.data.entity.Produto
import com.example.controlpedidos.data.repository.ProdutoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProdutoViewModel(
    private val repository: ProdutoRepository
) : ViewModel() {

    private val _produtos = MutableStateFlow<List<Produto>>(emptyList())
    val produtos: StateFlow<List<Produto>> = _produtos

    init {
        carregarProdutos()
    }

    private fun carregarProdutos() {
        viewModelScope.launch {
            repository.getAll().collect {
                _produtos.value = it
            }
        }
    }

    fun adicionarProduto(produto: Produto) {
        viewModelScope.launch {
            repository.insert(produto)
        }
    }

    fun atualizarProduto(produto: Produto) {
        viewModelScope.launch {
            repository.update(produto)
        }
    }

    fun removerProduto(produto: Produto) {
        viewModelScope.launch {
            repository.delete(produto)
        }
    }
}