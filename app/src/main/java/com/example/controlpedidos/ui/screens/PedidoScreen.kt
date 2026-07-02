package com.example.controlpedidos.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.controlpedidos.data.entity.Cliente
import com.example.controlpedidos.data.entity.Pedido
import com.example.controlpedidos.data.entity.Produto
import com.example.controlpedidos.ui.viewmodel.ClienteViewModel
import com.example.controlpedidos.ui.viewmodel.PedidoViewModel
import com.example.controlpedidos.ui.viewmodel.ProdutoViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PedidoScreen(
    viewModel: PedidoViewModel,
    clienteViewModel: ClienteViewModel,
    produtoViewModel: ProdutoViewModel
) {

    val pedidos by viewModel.pedidos.collectAsStateWithLifecycle()
    val clientes by clienteViewModel.clientes.collectAsStateWithLifecycle()
    val produtos by produtoViewModel.produtos.collectAsStateWithLifecycle()

    // 🔥 Date / Time Picker
    val datePickerState = rememberDatePickerState()
    val timePickerState = rememberTimePickerState()

    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    // Campos
    var quantidade by remember { mutableStateOf("") }
    var data by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }

    var clienteSelecionado by remember { mutableStateOf<Cliente?>(null) }
    var produtoSelecionado by remember { mutableStateOf<Produto?>(null) }

    val valorTotalCalculado =
        (quantidade.toIntOrNull() ?: 0) * (produtoSelecionado?.preco ?: 0.0)

    var expandirClientes by remember { mutableStateOf(false) }
    var expandirProdutos by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(12.dp))

        // CLIENTE
        OutlinedTextField(
            value = clienteSelecionado?.nome ?: "",
            onValueChange = {},
            readOnly = true,
            label = { Text("Cliente") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { expandirClientes = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Selecionar Cliente")
        }

        DropdownMenu(
            expanded = expandirClientes,
            onDismissRequest = { expandirClientes = false }
        ) {
            clientes.forEach { cliente ->
                DropdownMenuItem(
                    text = { Text(cliente.nome) },
                    onClick = {
                        clienteSelecionado = cliente
                        expandirClientes = false
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // PRODUTO
        OutlinedTextField(
            value = produtoSelecionado?.nome ?: "",
            onValueChange = {},
            readOnly = true,
            label = { Text("Produto") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { expandirProdutos = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Selecionar Produto")
        }

        DropdownMenu(
            expanded = expandirProdutos,
            onDismissRequest = { expandirProdutos = false }
        ) {
            produtos.forEach { produto ->
                DropdownMenuItem(
                    text = { Text(produto.nome) },
                    onClick = {
                        produtoSelecionado = produto
                        expandirProdutos = false
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // DATA / HORA
        Button(
            onClick = { showDatePicker = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Selecionar Data")
        }

        Button(
            onClick = { showTimePicker = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Selecionar Hora")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // CAMPOS
        OutlinedTextField(
            value = quantidade,
            onValueChange = { quantidade = it },
            label = { Text("Quantidade") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = String.format("%.2f", valorTotalCalculado),
            onValueChange = {},
            readOnly = true,
            label = { Text("Valor Total") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = status,
            onValueChange = { status = it },
            label = { Text("Status") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // SALVAR
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                if (clienteSelecionado != null &&
                    produtoSelecionado != null &&
                    quantidade.isNotBlank()
                ) {

                    viewModel.adicionarPedido(
                        Pedido(
                            clienteId = clienteSelecionado!!.id,
                            produtoId = produtoSelecionado!!.id,
                            quantidade = quantidade.toIntOrNull() ?: 0,
                            valorTotal = valorTotalCalculado,
                            data = data,
                            status = status
                        )
                    )

                    clienteSelecionado = null
                    produtoSelecionado = null
                    quantidade = ""
                    data = ""
                    status = ""
                }
            }
        ) {
            Text("Salvar Pedido")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Pedidos Cadastrados",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(pedidos) { pedido ->

                val clienteNome =
                    clientes.find { it.id == pedido.clienteId }?.nome ?: "Desconhecido"

                val produtoNome =
                    produtos.find { it.id == pedido.produtoId }?.nome ?: "Desconhecido"

                PedidoItem(
                    pedido = pedido,
                    clienteNome = clienteNome,
                    produtoNome = produtoNome,
                    onDelete = { viewModel.removerPedido(pedido) }
                )
            }
        }
    }

    // =========================
    // DATE PICKER (CORRETO)
    // =========================
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {

                        datePickerState.selectedDateMillis?.let { millis ->

                            val formatter =
                                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

                            data = formatter.format(Date(millis))
                        }

                        showDatePicker = false
                    }
                ) {
                    Text("OK")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    // =========================
    // TIME PICKER (CORRETO)
    // =========================
    if (showTimePicker) {
        AlertDialog(
            onDismissRequest = { showTimePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {

                        val hora =
                            "%02d:%02d".format(
                                timePickerState.hour,
                                timePickerState.minute
                            )

                        data = "$data $hora"

                        showTimePicker = false
                    }
                ) {
                    Text("OK")
                }
            },
            text = {
                TimePicker(state = timePickerState)
            }
        )
    }
}

@Composable
private fun PedidoItem(
    pedido: Pedido,
    clienteNome: String,
    produtoNome: String,
    onDelete: () -> Unit
) {

    Card(modifier = Modifier.fillMaxWidth()) {

        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = "Pedido #${pedido.id}",
                style = MaterialTheme.typography.titleMedium
            )

            Text("Cliente: $clienteNome")
            Text("Produto: $produtoNome")
            Text("Quantidade: ${pedido.quantidade}")
            Text("Valor Total: R$ ${pedido.valorTotal}")
            Text("Data: ${pedido.data}")
            Text("Status: ${pedido.status}")

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = onDelete) {
                Text("Excluir")
            }
        }
    }
}