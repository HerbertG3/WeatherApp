package com.weatherapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weatherapp.ui.theme.WeatherAppTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RegisterPage()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun RegisterPage(modifier: Modifier = Modifier) {
    var nome by rememberSaveable { mutableStateOf("") }
    Spacer(modifier = Modifier.size(24.dp))
    var email by rememberSaveable { mutableStateOf("") }
    Spacer(modifier = Modifier.size(24.dp))
    var password by rememberSaveable { mutableStateOf("") }
    Spacer(modifier = Modifier.size(24.dp))
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    Spacer(modifier = Modifier.size(24.dp))
    val activity = LocalContext.current as? Activity
    Column( verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp),
    ) {
        Text(
            text = "Registro",
            fontSize = 24.sp
        )
        OutlinedTextField(
            value = nome,
            label = { Text(text = "Digite seu nome") },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { nome = it }
        )
        OutlinedTextField(
            value = email,
            label = { Text(text = "Digite seu e-mail") },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { email = it }
        )
        OutlinedTextField(
            value = password,
            label = { Text(text = "Digite sua senha") },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation()
        )
        OutlinedTextField(
            value = confirmPassword,
            label = { Text(text = "Confirme sua senha") },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { confirmPassword = it },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.size(24.dp))
        Row(modifier = modifier) {
            Button(
                onClick = {
                    Toast.makeText(activity, "Usuário Registrado!", Toast.LENGTH_LONG).show()
                    activity?.startActivity(
                        Intent(activity, LoginActivity::class.java).setFlags(
                            Intent.FLAG_ACTIVITY_SINGLE_TOP
                        )
                    )
                },
                enabled = email.isNotEmpty() && password.isNotEmpty() && nome.isNotEmpty() && password == confirmPassword
            ) {
                Text("Registrar")
            }
            Spacer(modifier = Modifier.size(24.dp))
            Button(
                onClick = { email = ""; password = ""; nome = ""; confirmPassword = "" }
            ) {
                Text("Limpar")
            }
        }
    }
}