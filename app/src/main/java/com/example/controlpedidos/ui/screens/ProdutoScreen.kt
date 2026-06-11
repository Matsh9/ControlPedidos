package com.example.controlpedidos.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.controlpedidos.data.entity.Produto
import com.example.controlpedidos.ui.viewmodel.ProdutoViewModel

@Composable
fun ProdutoScreen(
    viewModel: ProdutoViewModel
) {

    val produtos by viewModel.produtos
        .collectAsStateWithLifecycle()

    var nome by remember {
        mutableStateOf("")
    }

    var descricao by remember {
        mutableStateOf("")
    }

    var preco by remember {
        mutableStateOf("")
    }

    var estoque by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Cadastro de Produtos",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        OutlinedTextField(
            value = nome,
            onValueChange = {
                nome = it
            },
            label = {
                Text("Nome do Produto")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = descricao,
            onValueChange = {
                descricao = it
            },
            label = {
                Text("Descrição")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = preco,
            onValueChange = {
                preco = it
            },
            label = {
                Text("Preço")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = estoque,
            onValueChange = {
                estoque = it
            },
            label = {
                Text("Estoque")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                if (
                    nome.isNotBlank() &&
                    preco.isNotBlank() &&
                    estoque.isNotBlank()
                ) {

                    viewModel.adicionarProduto(
                        Produto(
                            nome = nome,
                            descricao = descricao,
                            preco = preco.toDoubleOrNull() ?: 0.0,
                            estoque = estoque.toIntOrNull() ?: 0
                        )
                    )

                    nome = ""
                    descricao = ""
                    preco = ""
                    estoque = ""
                }
            }
        ) {

            Text("Salvar Produto")
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = "Produtos Cadastrados",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        LazyColumn(
            verticalArrangement =
                Arrangement.spacedBy(8.dp)
        ) {

            items(produtos) { produto ->

                ProdutoItem(
                    produto = produto,
                    onDelete = {
                        viewModel.removerProduto(
                            produto
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun ProdutoItem(
    produto: Produto,
    onDelete: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = produto.nome,
                style =
                    MaterialTheme
                        .typography
                        .titleMedium
            )

            Text(
                text = produto.descricao
            )

            Text(
                text = "Preço: R$ ${produto.preco}"
            )

            Text(
                text = "Estoque: ${produto.estoque}"
            )

            HorizontalDivider(
                modifier = Modifier.padding(
                    vertical = 8.dp
                )
            )

            Row {

                Button(
                    onClick = onDelete
                ) {

                    Text("Excluir")
                }
            }
        }
    }
}