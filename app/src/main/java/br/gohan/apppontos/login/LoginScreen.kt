package br.gohan.apppontos.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(
    authService: FirebaseAuth,
    viewModel: LoginViewModel = viewModel(),
    nextScreen: () -> Unit,
) {
    viewModel.setAuthService(authService)

    val keyboardController = LocalSoftwareKeyboardController.current
    val notification = remember { SnackbarHostState() }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(
        snackbarHost = {
            SnackbarHost(notification)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Login", fontSize = 20.sp)
            Spacer(Modifier.height(32.dp))
            TextField(
                email,
                label = { Text("Email")},
                onValueChange = {
                    email = it
                })
            TextField(
                value = password,
                visualTransformation = PasswordVisualTransformation(),
                label = { Text("Password")},
                onValueChange = {
                    password = it
                })
            Spacer(Modifier.height(32.dp))
            Button(
                modifier = Modifier.width(280.dp),
                onClick = {
                    keyboardController?.hide()
                    viewModel.loginUser(email, password, loginResult = { message ->
                        if (message == null) {
                            nextScreen.invoke()
                        } else {
                            viewModel.showNotification(notification, "Login failed: $message")
                        }
                    })
                }) {
                Text("Accept")
            }
            Spacer(Modifier.height(8.dp))
            OutlinedButton(
                modifier = Modifier.width(280.dp),
                onClick = {
                    keyboardController?.hide()
                    viewModel.registerNewUser(email, password, registerResult = { message ->
                        if (message == null) {
                            viewModel.showNotification(notification, "User registered")
                        } else {
                            viewModel.showNotification(notification, "Register failed: $message")
                        }
                    })
                }) {
                Text("Register")
            }
        }
    }
}