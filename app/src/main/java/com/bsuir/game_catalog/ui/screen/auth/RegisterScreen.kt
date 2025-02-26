package com.bsuir.game_catalog.ui.screen.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.bsuir.game_catalog.Routes
import com.bsuir.game_catalog.ui.component.auth.AuthBox
import com.bsuir.game_catalog.ui.component.auth.card.RegisterCard
import com.bsuir.game_catalog.viewmodel.AuthViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RegisterScreen(
    authViewModel: AuthViewModel,
    navController: NavController,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val errorMessage by authViewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        authViewModel.user.collectLatest { user ->
            if (user != null) {
                navController.navigate(Routes.MAIN) {
                    popUpTo(Routes.AUTH) { inclusive = true }
                }
            }
        }
    }

    AuthBox {
        RegisterCard(
            email = email,
            onEmailChange = { email = it },
            password = password,
            onPasswordChange = { password = it },
            errorMessage = errorMessage,
            onSignUp = { authViewModel.signUp(email, password) },
            onToggleCard = { navController.navigate(Routes.AUTH) }
        )
    }
}
