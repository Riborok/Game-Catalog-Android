package com.bsuir.game_catalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bsuir.game_catalog.ui.screen.auth.LoginScreen
import com.bsuir.game_catalog.ui.screen.auth.RegisterScreen
import com.bsuir.game_catalog.ui.theme.GameCatalogTheme
import com.bsuir.game_catalog.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameCatalogTheme {
                val authViewModel = remember {
                    ViewModelProvider(
                        this,
                        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
                    )[AuthViewModel::class.java]
                }
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = if (authViewModel.user.value != null) Routes.MAIN else Routes.AUTH
                ) {
                    composable(Routes.AUTH) {
                        LoginScreen(
                            authViewModel = authViewModel,
                            navController = navController,
                        )
                    }
                    composable(Routes.REGISTER) {
                        RegisterScreen(
                            authViewModel = authViewModel,
                            navController = navController,
                        )
                    }
                    composable(Routes.MAIN) {
                        MainContent(
                            authViewModel = authViewModel,
                            navController = navController,
                        )
                    }
                }
            }
        }
    }
}