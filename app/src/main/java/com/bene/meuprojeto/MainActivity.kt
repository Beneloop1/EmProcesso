package com.bene.meuprojeto

import TelaInicio.TelaInicio
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bene.meuprojeto.ui.theme.MeuProjetoTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import resultado.TelaResultado
import telaestudos.TelaBemVindos
import telaBemVindos.TelaInicial
import login.LoginScreen
import teleregistro.TelaRegistro


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeuProjetoTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "inicial") {
                    composable("inicial") { TelaInicial(navController) }
                    composable("login") { LoginScreen(navController) }
                    composable("TelaRegistro") { TelaRegistro(navController) }
                    composable("telainicio") { TelaInicio(navController) }
                    composable("estudo") { TelaBemVindos(navController) }
                    composable("resultado") { TelaResultado() }
                }
            }
        }
    }
}

