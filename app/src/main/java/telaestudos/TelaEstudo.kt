package telaestudos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bene.meuprojeto.R
import androidx.compose.ui.graphics.Color

// TELA 2 – Tela de Estudo
@Composable
fun TelaBemVindos(navController: NavHostController) {

    val resposta = remember { mutableStateOf("") }
    Image(
        painter = painterResource(id = R.drawable.fundotela), // Altere para o nome do arquivo de sua imagem
        contentDescription = "Tela Estudo",
        modifier = Modifier.fillMaxSize(), // Faz a imagem ocupar toda a tela
        contentScale = ContentScale.Crop // Faz a imagem preencher toda a tela
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Traduza: Eu gosto de maçã",
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF6200EE)
        )

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = resposta.value,
            onValueChange = { resposta.value = it },
            label = { Text("Digite em inglês") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp) // adicionando um paddind opcional
                .clip(RoundedCornerShape(50.dp)) // borda arredondada
                .background(color = Color.Green)

        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            navController.navigate("resultado")
        }) {
            Text("Enviar resposta")
        }
    }
}