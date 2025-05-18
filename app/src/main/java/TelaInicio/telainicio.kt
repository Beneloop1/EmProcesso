package TelaInicio

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.Text
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.ui.layout.ContentScale
import com.bene.meuprojeto.R
import androidx.compose.ui.text.TextStyle


@Composable
fun TelaInicio(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.fundotela), // Altere para o nome do arquivo de sua imagem
            contentDescription = "Fundo da tela inicial",
            modifier = Modifier.fillMaxSize(), // Faz a imagem ocupar toda a tela
            contentScale = ContentScale.Crop // Faz a imagem preencher toda a tela
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Espaço para abaixar a pontuação
            Spacer(modifier = Modifier.height(32.dp)) // aumenta esse valor se quiser descer mais

            // Topo: Pontuação e Nome
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Pontuação: 120",
                    fontSize = 16.sp,
                    color = Color.White
                )
                Text(
                    text = "Lucas",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White

                )
            }

            Spacer(modifier = Modifier.height(54.dp))

            // Imagem da coruja
            Image(
                painter = painterResource(id = R.drawable.lendo), // coloque uma imagem chamada coruja.png em drawable
                contentDescription = "foguete",
                modifier = Modifier.size(350.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Troféus

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth(0.8f) // 80% da largura para centralizar visualmente
                ) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(onClick = { navController.navigate("estudo") }) {
                            Image(
                                painter = painterResource(id = R.drawable.livro),
                                contentDescription = "Iniciante",
                                modifier = Modifier.size(64.dp)
                            )
                        }
                        Text("Iniciante", fontSize = 14.sp, color = Color.White)
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(onClick = { navController.navigate("") }) {
                            Image(
                                painter = painterResource(id = R.drawable.memoria),
                                contentDescription = "Memory",
                                modifier = Modifier.size(64.dp)
                            )
                        }
                        Text("Memory", fontSize = 14.sp, color = Color.White)
                    }
                }
            }
        }
    }
}




