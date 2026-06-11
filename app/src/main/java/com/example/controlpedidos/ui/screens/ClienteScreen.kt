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
import com.example.controlpedidos.data.entity.Cliente
import com.example.controlpedidos.ui.viewmodel.ClienteViewModel

@Composable
fun ClienteScreen(
    viewModel: ClienteViewModel
) {

    val clientes by viewModel.clientes
        .collectAsStateWithLifecycle()

    var nome by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var telefone by remember {
        mutableStateOf("")
    }

    var endereco by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Cadastro de Clientes",
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
                Text("Nome")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text("Email")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = telefone,
            onValueChange = {
                telefone = it
            },
            label = {
                Text("Telefone")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = endereco,
            onValueChange = {
                endereco = it
            },
            label = {
                Text("Endereço")
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
                    email.isNotBlank()
                ) {

                    viewModel.adicionarCliente(
                        Cliente(
                            nome = nome,
                            email = email,
                            telefone = telefone,
                            endereco = endereco
                        )
                    )

                    nome = ""
                    email = ""
                    telefone = ""
                    endereco = ""
                }
            }
        ) {

            Text("Salvar Cliente")
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = "Clientes Cadastrados",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        LazyColumn(
            verticalArrangement =
                Arrangement.spacedBy(8.dp)
        ) {

            items(clientes) { cliente ->

                ClienteItem(
                    cliente = cliente,
                    onDelete = {
                        viewModel.removerCliente(
                            cliente
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun ClienteItem(
    cliente: Cliente,
    onDelete: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = cliente.nome,
                style =
                    MaterialTheme
                        .typography
                        .titleMedium
            )

            Text(
                text = cliente.email
            )

            Text(
                text = cliente.telefone
            )

            Text(
                text = cliente.endereco
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