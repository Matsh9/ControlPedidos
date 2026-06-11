package com.example.controlpedidos.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.controlpedidos.data.dao.ClienteDao
import com.example.controlpedidos.data.dao.PedidoDao
import com.example.controlpedidos.data.dao.ProdutoDao
import com.example.controlpedidos.data.dao.UsuarioDao
import com.example.controlpedidos.data.entity.Cliente
import com.example.controlpedidos.data.entity.Pedido
import com.example.controlpedidos.data.entity.Produto
import com.example.controlpedidos.data.entity.Usuario

@Database(
    entities = [
        Cliente::class,
        Produto::class,
        Pedido::class,
        Usuario::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun clienteDao(): ClienteDao

    abstract fun produtoDao(): ProdutoDao

    abstract fun pedidoDao(): PedidoDao

    abstract fun usuarioDao(): UsuarioDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "control_pedidos_db"
                ).build()

                INSTANCE = instance

                instance
            }
        }
    }
}