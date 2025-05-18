package telaBemVindos


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bene.meuprojeto.R




@Composable
fun TelaInicial(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        //imagem fundo
        Image(
            painter = painterResource(id = R.drawable.fundotela), // Altere para o nome do arquivo de sua imagem
            contentDescription = "Fundo da tela inicial",
            modifier = Modifier.fillMaxSize(), // Faz a imagem ocupar toda a tela
            contentScale = ContentScale.Crop // Faz a imagem preencher toda a tela
        )
        // CONTEUDO da tela sobre o fundo centralizado
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center, // Centraliza verticalmente
            horizontalAlignment = Alignment.CenterHorizontally // Centraliza horizontalmente

        ) {

            Spacer(modifier = Modifier.height(10.dp))

            Image(
                painter = painterResource(id = R.drawable.vindosbem), // coloque uma imagem chamada coruja.png em drawable
                contentDescription = "lendo",
                modifier = Modifier
                    .size(500.dp)
                    .offset(y = (-60).dp) // move 40dp para cima
            )

            Spacer(modifier = Modifier.height(0.dp))

            Text(
                text = "Bem-vindo",
                modifier = Modifier.offset(y = (-100).dp),
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 60.sp
                ),
                color = Color(0xFF3F5EFB) // Cor do texto para contraste com o fundo
            )

            Spacer(modifier = Modifier.height(0.dp))

            Button(
                onClick = { navController.navigate("login") },
                modifier = Modifier.offset(y = (-40).dp) // sobe o botao
            ) {
                Text("Começar")
            }
        }
    }
}


