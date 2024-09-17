package br.gohan.apppontos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.gohan.apppontos.graph.GraphScreen
import br.gohan.apppontos.login.LoginScreen
import br.gohan.apppontos.ui.theme.AppPontosTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    private lateinit var authService: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authService = FirebaseAuth.getInstance()

        setContent {
            AppPontosTheme {
                val navController = rememberNavController()
                val isUserLogged = authService.currentUser

                val startScreen = if (isUserLogged == null) {
                    "login"
                } else {
                    "graph"
                }

                // Navigation in screens
                NavHost(navController, startDestination = startScreen) {
                    composable("login") {
                        LoginScreen(authService, nextScreen = {
                            navController.navigate("graph")
                        })
                    }
                    composable("graph") {
                        GraphScreen(authService.currentUser?.email, backButton = {
                            authService.signOut()
                            navController.navigate("login")
                        })
                    }
                }
            }
        }
    }
}

