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
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.controlpedidos.ui.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    navController: NavHostController
) {

    val preferences by viewModel
        .userPreferences
        .collectAsStateWithLifecycle(
            initialValue = null
        )

    val user = preferences

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement =
            Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Configurações",
            style =
                MaterialTheme
                    .typography
                    .headlineSmall
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {

                Text(
                    text = "Usuário Logado",
                    style =
                        MaterialTheme
                            .typography
                            .titleMedium
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text =
                        "Nome: ${user?.userName ?: ""}"
                )

                Text(
                    text =
                        "Email: ${user?.userEmail ?: ""}"
                )

                Text(
                    text =
                        "ID: ${user?.userId ?: 0}"
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {

                Text(
                    text = "Aparência",
                    style =
                        MaterialTheme
                            .typography
                            .titleMedium
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "Tema Escuro"
                )

                Switch(
                    checked =
                        user?.darkTheme ?: false,

                    onCheckedChange = {

                        viewModel.alterarTema(
                            it
                        )
                    }
                )
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(),

            onClick = {

                viewModel.logout()

                navController.navigate(
                    "login"
                ) {

                    popUpTo(0)

                    launchSingleTop = true
                }
            }
        ) {

            Text(
                text = "Sair"
            )
        }
    }
}