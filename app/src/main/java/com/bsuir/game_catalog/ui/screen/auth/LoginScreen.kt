package com.bsuir.game_catalog.ui.screen.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.bsuir.game_catalog.Routes
import com.bsuir.game_catalog.ui.component.auth.AuthBox
import com.bsuir.game_catalog.ui.component.auth.card.LoginCard
import com.bsuir.game_catalog.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    navController: NavController,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val errorMessage by authViewModel.errorMessage.observeAsState()
    val user by authViewModel.user.observeAsState()

    LaunchedEffect(user) {
        if (user != null) {
            navController.navigate(Routes.MAIN) {
                popUpTo(Routes.AUTH) { inclusive = true }
            }
        }
    }

    AuthBox {
        LoginCard(
            email = email,
            onEmailChange = { email = it },
            password = password,
            onPasswordChange = { password = it },
            errorMessage = errorMessage,
            onSignIn = { authViewModel.signIn(email, password) },
            onToggleCard = { navController.navigate(Routes.REGISTER) }
        )
    }
}
