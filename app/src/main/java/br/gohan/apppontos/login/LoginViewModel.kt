package br.gohan.apppontos.login

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private lateinit var authService: FirebaseAuth

    fun setAuthService(authService: FirebaseAuth) {
        this.authService = authService
    }

    fun loginUser(email: String, password: String, loginResult: (String?) -> Unit) {
        authService.signInWithEmailAndPassword(email, password).addOnCompleteListener { login ->
            if (login.isSuccessful) {
                loginResult(null)
            } else {
                loginResult(login.exception?.message)
            }
        }
    }

    fun registerNewUser(email: String, password: String, registerResult: (String?) -> Unit) {
        authService.createUserWithEmailAndPassword(email, password).addOnCompleteListener { register ->
            if (register.isSuccessful) {
                registerResult(null)
            } else {
                registerResult(register.exception?.message)
            }
        }
    }

    fun showNotification(notification: SnackbarHostState, message: String) {
        viewModelScope.launch {
            notification.showSnackbar(message)
        }
    }
}