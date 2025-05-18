package teleregistro


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.bene.meuprojeto.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import androidx.compose.ui.text.TextStyle


@Composable
fun TelaRegistro(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        //imagem fundo
        Image(
            painter = painterResource(id = R.drawable.fundotela), // Altere para o nome do arquivo de sua imagem
            contentDescription = "Fundo da tela inicial",
            modifier = Modifier.fillMaxSize(), // Faz a imagem ocupar toda a tela
            contentScale = ContentScale.Crop // Faz a imagem preencher toda a tela
        )

        val context = LocalContext.current
        val auth = FirebaseAuth.getInstance()

        var email by remember { mutableStateOf("") }
        var senha by remember { mutableStateOf("") }
        var mensagem by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Cadastro",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFFA11DD1)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                shape = RoundedCornerShape(50.dp),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                textStyle = TextStyle(color = Color.White)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = senha,
                onValueChange = { senha = it },
                label = { Text("Senha") },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(50.dp),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                textStyle = TextStyle(color = Color.White)

            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (email.isBlank() || senha.length < 6) {
                        mensagem = "Preencha os campos corretamente (mínimo 6 caracteres)"
                        return@Button
                    }

                    auth.createUserWithEmailAndPassword(email, senha)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                mensagem = "Cadastro realizado com sucesso!"
                                //navega direto pro login

                                val uid = auth.currentUser?.uid
                                val db = Firebase.firestore

                                val perfil = hashMapOf(
                                    "email" to email,
                                    "pontos" to 0,
                                    "nivel" to "iniciante"
                                )
                                uid?.let {
                                    db.collection("usuarios").document(it).set(perfil)
                                }
                                // navega pra tela de login
                                navController.navigate("login") { // volta para tela de login
                                    popUpTo(0) { inclusive = true }
                                }
                            } else {
                                mensagem = task.exception?.message ?: "Erro desconhecido"
                            }
                        }
                },
            ) {
                Text("Cadastrar")
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (mensagem.isNotEmpty()) {
                Text(
                    text = mensagem,
                    color = Color.Yellow,
                    modifier = Modifier.padding(8.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                TextButton(onClick = {
                    navController.navigate("login")
                }) {
                    Text("Já tem uma conta? Faça login", color = Color.White)
                }

            }
        }
    }
}

