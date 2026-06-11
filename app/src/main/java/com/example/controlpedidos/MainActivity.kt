package com.example.controlpedidos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.controlpedidos.ui.theme.ControlPedidosTheme
import androidx.navigation.compose.rememberNavController
import com.example.controlpedidos.data.database.AppDatabase
import com.example.controlpedidos.data.datastore.DataStoreModule
import com.example.controlpedidos.data.repository.ClienteRepository
import com.example.controlpedidos.data.repository.PedidoRepository
import com.example.controlpedidos.data.repository.ProdutoRepository
import com.example.controlpedidos.data.repository.UsuarioRepository
import com.example.controlpedidos.navigation.AppNavGraph
import com.example.controlpedidos.ui.viewmodel.ClienteViewModel
import com.example.controlpedidos.ui.viewmodel.LoginViewModel
import com.example.controlpedidos.ui.viewmodel.PedidoViewModel
import com.example.controlpedidos.ui.viewmodel.ProdutoViewModel
import com.example.controlpedidos.ui.viewmodel.SettingsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.controlpedidos.data.entity.Usuario

class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        val database =
            AppDatabase.getDatabase(this)

        val clienteRepository =
            ClienteRepository(
                database.clienteDao()
            )

        val produtoRepository =
            ProdutoRepository(
                database.produtoDao()
            )

        val pedidoRepository =
            PedidoRepository(
                database.pedidoDao()
            )

        val usuarioRepository =
            UsuarioRepository(
                database.usuarioDao()
            )

        CoroutineScope(Dispatchers.IO).launch {

            usuarioRepository.insert(
                Usuario(
                    nome = "Administrador",
                    email = "admin@controlepedidos.com",
                    senha = "123456"
                )
            )
        }

        val preferencesRepository =
            DataStoreModule
                .provideUserPreferencesRepository(
                    this
                )

        val clienteViewModel =
            ClienteViewModel(
                clienteRepository
            )

        val produtoViewModel =
            ProdutoViewModel(
                produtoRepository
            )

        val pedidoViewModel =
            PedidoViewModel(
                pedidoRepository
            )

        val loginViewModel =
            LoginViewModel(
                usuarioRepository,
                preferencesRepository
            )

        val settingsViewModel =
            SettingsViewModel(
                preferencesRepository
            )

        setContent {

            val preferences by settingsViewModel
                .userPreferences
                .collectAsStateWithLifecycle(
                    initialValue = null
                )

            ControlPedidosTheme(
                darkTheme =
                    preferences?.darkTheme ?: false
            ) {

                val navController =
                    rememberNavController()

                Surface {

                    AppNavGraph(
                        navController = navController,
                        clienteViewModel = clienteViewModel,
                        produtoViewModel = produtoViewModel,
                        pedidoViewModel = pedidoViewModel,
                        loginViewModel = loginViewModel,
                        settingsViewModel = settingsViewModel
                    )
                }
            }
        }
    }
}