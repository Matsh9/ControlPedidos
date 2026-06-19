package com.example.controlpedidos.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.controlpedidos.ui.viewmodel.LoginViewModel

@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: LoginViewModel
) {

    var nome by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var senha by remember {
        mutableStateOf("")
    }

    var mensagem by remember {
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
            text = "Criar Conta",
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
                    modifier = Modifier.height(12.dp)
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
                    modifier = Modifier.height(12.dp)
                )

                OutlinedTextField(
                    value = senha,
                    onValueChange = {
                        senha = it
                    },
                    label = {
                        Text("Senha")
                    },
                    visualTransformation =
                        PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                if (mensagem.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = mensagem,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Button(
                    modifier = Modifier.fillMaxWidth(),

                    onClick = {

                        if (
                            nome.isBlank() ||
                            email.isBlank() ||
                            senha.isBlank()
                        ) {

                            mensagem =
                                "Preencha todos os campos"

                            return@Button
                        }

                        viewModel.registrar(
                            nome = nome,
                            email = email,
                            senha = senha,
                            onSuccess = {
                                navController.navigate("login")
                            },
                            onError = { msg ->
                                mensagem = msg
                            }
                        )
                    }
                ) {

                    Text("Cadastrar")
                }

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                TextButton(
                    modifier = Modifier.fillMaxWidth(),

                    onClick = {

                        navController.popBackStack()
                    }
                ) {

                    Text(
                        "Já possui conta? Entrar"
                    )
                }
            }
        }
    }
}