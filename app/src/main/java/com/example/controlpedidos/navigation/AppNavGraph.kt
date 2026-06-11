package com.example.controlpedidos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.controlpedidos.ui.screens.ClienteScreen
import com.example.controlpedidos.ui.screens.HomeScreen
import com.example.controlpedidos.ui.screens.LoginScreen
import com.example.controlpedidos.ui.screens.PedidoScreen
import com.example.controlpedidos.ui.screens.ProdutoScreen
import com.example.controlpedidos.ui.screens.SettingsScreen
import com.example.controlpedidos.ui.viewmodel.ClienteViewModel
import com.example.controlpedidos.ui.viewmodel.LoginViewModel
import com.example.controlpedidos.ui.viewmodel.PedidoViewModel
import com.example.controlpedidos.ui.viewmodel.ProdutoViewModel
import com.example.controlpedidos.ui.viewmodel.SettingsViewModel

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

            LoginScreen(
                navController = navController,
                viewModel = loginViewModel
            )
        }

        composable("home") {

            HomeScreen(
                navController = navController
            )
        }

        composable("clientes") {

            ClienteScreen(
                viewModel = clienteViewModel
            )
        }

        composable("produtos") {

            ProdutoScreen(
                viewModel = produtoViewModel
            )
        }

        composable("pedidos") {

            PedidoScreen(
                viewModel = pedidoViewModel
            )
        }

        composable("settings") {

            SettingsScreen(
                viewModel = settingsViewModel,
                navController = navController
            )
        }
    }
}