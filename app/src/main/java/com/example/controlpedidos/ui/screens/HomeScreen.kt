package com.example.controlpedidos.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Button(
                    modifier = Modifier.fillMaxWidth(),

                    onClick = {
                        navController.navigate(
                            "clientes"
                        )
                    }
                ) {
                    Text("Clientes")
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),

                    onClick = {
                        navController.navigate(
                            "produtos"
                        )
                    }
                ) {
                    Text("Produtos")
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),

                    onClick = {
                        navController.navigate(
                            "pedidos"
                        )
                    }
                ) {
                    Text("Pedidos")
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),

                    onClick = {
                        navController.navigate(
                            "settings"
                        )
                    }
                ) {
                    Text("Configurações")
                }
            }
        }
    }
}