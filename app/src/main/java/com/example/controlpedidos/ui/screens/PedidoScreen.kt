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
import com.example.controlpedidos.data.entity.Pedido
import com.example.controlpedidos.ui.viewmodel.PedidoViewModel

@Composable
fun PedidoScreen(
    viewModel: PedidoViewModel
) {

    val pedidos by viewModel.pedidos
        .collectAsStateWithLifecycle()

    var clienteId by remember {
        mutableStateOf("")
    }

    var produtoId by remember {
        mutableStateOf("")
    }

    var quantidade by remember {
        mutableStateOf("")
    }

    var valorTotal by remember {
        mutableStateOf("")
    }

    var data by remember {
        mutableStateOf("")
    }

    var status by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Cadastro de Pedidos",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        OutlinedTextField(
            value = clienteId,
            onValueChange = {
                clienteId = it
            },
            label = {
                Text("ID do Cliente")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = produtoId,
            onValueChange = {
                produtoId = it
            },
            label = {
                Text("ID do Produto")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = quantidade,
            onValueChange = {
                quantidade = it
            },
            label = {
                Text("Quantidade")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = valorTotal,
            onValueChange = {
                valorTotal = it
            },
            label = {
                Text("Valor Total")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = data,
            onValueChange = {
                data = it
            },
            label = {
                Text("Data")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = status,
            onValueChange = {
                status = it
            },
            label = {
                Text("Status")
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
                    clienteId.isNotBlank() &&
                    produtoId.isNotBlank() &&
                    quantidade.isNotBlank()
                ) {

                    viewModel.adicionarPedido(
                        Pedido(
                            clienteId =
                                clienteId.toIntOrNull() ?: 0,

                            produtoId =
                                produtoId.toIntOrNull() ?: 0,

                            quantidade =
                                quantidade.toIntOrNull() ?: 0,

                            valorTotal =
                                valorTotal.toDoubleOrNull() ?: 0.0,

                            data = data,

                            status = status
                        )
                    )

                    clienteId = ""
                    produtoId = ""
                    quantidade = ""
                    valorTotal = ""
                    data = ""
                    status = ""
                }
            }
        ) {

            Text("Salvar Pedido")
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = "Pedidos Cadastrados",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        LazyColumn(
            verticalArrangement =
                Arrangement.spacedBy(8.dp)
        ) {

            items(pedidos) { pedido ->

                PedidoItem(
                    pedido = pedido,
                    onDelete = {
                        viewModel.removerPedido(
                            pedido
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun PedidoItem(
    pedido: Pedido,
    onDelete: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = "Pedido #${pedido.id}",
                style =
                    MaterialTheme
                        .typography
                        .titleMedium
            )

            Text(
                text = "Cliente ID: ${pedido.clienteId}"
            )

            Text(
                text = "Produto ID: ${pedido.produtoId}"
            )

            Text(
                text = "Quantidade: ${pedido.quantidade}"
            )

            Text(
                text = "Valor Total: R$ ${pedido.valorTotal}"
            )

            Text(
                text = "Data: ${pedido.data}"
            )

            Text(
                text = "Status: ${pedido.status}"
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