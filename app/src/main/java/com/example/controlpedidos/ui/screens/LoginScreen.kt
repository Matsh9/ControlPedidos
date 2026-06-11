package com.example.controlpedidos.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.controlpedidos.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel
) {

    var email by remember {
        mutableStateOf("")
    }

    var senha by remember {
        mutableStateOf("")
    }

    var erro by remember {
        mutableStateOf("")
    }

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

                OutlinedTextField(
                    value = senha,
                    onValueChange = {
                        senha = it
                    },
                    label = {
                        Text("Senha")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                )

                if (erro.isNotEmpty()) {

                    Text(
                        text = erro,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),

                    onClick = {

                        viewModel.login(
                            email = email,
                            senha = senha,

                            onSuccess = {

                                navController.navigate(
                                    "home"
                                ) {

                                    popUpTo("login") {
                                        inclusive = true
                                    }
                                }
                            },

                            onError = {
                                erro =
                                    "Email ou senha inválidos"
                            }
                        )
                    }
                ) {

                    Text("Entrar")
                }
            }
        }
    }
}