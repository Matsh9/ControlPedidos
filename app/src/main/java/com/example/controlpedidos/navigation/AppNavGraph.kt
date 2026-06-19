package com.example.controlpedidos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.controlpedidos.ui.screens.*
import com.example.controlpedidos.ui.viewmodel.*

@Composable
fun AppNavGraph(
    navController: NavHostController,
    clienteViewModel: ClienteViewModel,
    produtoViewModel: ProdutoViewModel,
    pedidoViewModel: PedidoViewModel,
    loginViewModel: LoginViewModel,
    settingsViewModel: SettingsViewModel
) {

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            LoginScreen(navController, loginViewModel)
        }

        composable("registro") {
            RegisterScreen(navController, loginViewModel)
        }

        composable("home") {
            HomeScreen(navController)
        }

        composable("clientes") {
            ClienteScreen(clienteViewModel)
        }

        composable("produtos") {
            ProdutoScreen(produtoViewModel)
        }

        composable("pedidos") {
            PedidoScreen(
                viewModel = pedidoViewModel,
                clienteViewModel = clienteViewModel,
                produtoViewModel = produtoViewModel
            )
        }

        composable("settings") {
            SettingsScreen(settingsViewModel, navController)
        }
    }
}