package com.example.controlpedidos.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    navController: NavHostController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Controle de Pedidos",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate("clientes") }
        ) {
            Text("Clientes")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate("produtos") }
        ) {
            Text("Produtos")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate("pedidos") }
        ) {
            Text("Pedidos")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate("settings") }
        ) {
            Text("Configurações")
        }
    }
}