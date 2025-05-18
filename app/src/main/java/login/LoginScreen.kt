package login


import android.R.attr.end
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.bene.meuprojeto.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import androidx.compose.ui.text.TextStyle


@Composable
fun LoginScreen(navController: NavHostController) {
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var mensagem by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        //imagem fundo
        Image(
            painter = painterResource(id = R.drawable.fundotela), // Altere para o nome do arquivo de sua imagem
            contentDescription = "Fundo da tela inicial",
            modifier = Modifier.fillMaxSize(), // Faz a imagem ocupar toda a tela
            contentScale = ContentScale.Crop // Faz a imagem preencher toda a tela
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Login",
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

            Spacer(modifier = Modifier.height(24.dp))

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

            Button(onClick = {
                if (email.isBlank() || senha.isBlank()) {
                    mensagem = "Preencha todos os campos."
                    return@Button
                }

                auth.signInWithEmailAndPassword(email, senha)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val uid = auth.currentUser?.uid
                            val db = Firebase.firestore

                            uid?.let {
                                db.collection("usuarios").document(uid).get()
                                    .addOnSuccessListener { document ->
                                        if (document.exists()) {
                                            val email = document.getString("email") ?: ""
                                            val pontos = document.getLong("pontos") ?: 0
                                            val nivel = document.getString("nivel") ?: ""

                                            // Aqui você pode usar os dados se quiser

                                            mensagem = "Login realizado com sucesso!"
                                            navController.navigate("telainicio") {
                                                popUpTo(0) { inclusive = true }
                                            }
                                        }else{
                                            mensagem = "Perfil não encontrado"
                                        }
                                    }
                                    .addOnFailureListener {
                                        mensagem = "Erro ao carregar perfil."
                                    }
                            }
                        } else {
                            mensagem = task.exception?.message ?: "Erro ao fazer login."
                        }
                    }

            }) {
                Text("Entrar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (mensagem.isNotEmpty()) {
                Text(text = mensagem, color = Color.Yellow)
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = {
                navController.navigate("telaregistro")
            }) {
               val annotatedText = buildAnnotatedString {
                   append("não tem uma conta? ")

                   //parte clicavel
                   pushStringAnnotation(tag = "CADASTRO", annotation = "Cadastro")
                   withStyle(style = SpanStyle(color = Color(0xFF3F5EFB))){
                       append("Cadastre-se")
                   }
                   pop()
               }

                ClickableText(
                    text = annotatedText,
                    onClick = { offset ->
                        annotatedText.getStringAnnotations(tag = "CADASTRO", start = offset, end = offset)
                            .firstOrNull()?.let {
                                navController.navigate("TelaRegistro")
                            }
                    },
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.White, fontSize = 16.sp)
                )
            }
        }
    }
}
