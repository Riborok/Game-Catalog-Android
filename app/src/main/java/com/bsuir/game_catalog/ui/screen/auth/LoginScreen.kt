package com.bsuir.game_catalog.ui.screen.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.bsuir.game_catalog.utils.Route
import com.bsuir.game_catalog.ui.Background
import com.bsuir.game_catalog.ui.component.auth.card.LoginCard
import com.bsuir.game_catalog.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    navController: NavController,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val errorMessage by authViewModel.errorMessage.collectAsState()

    Background {
        LoginCard(
            email = email,
            onEmailChange = { email = it },
            password = password,
            onPasswordChange = { password = it },
            errorMessage = errorMessage,
            onSignIn = { authViewModel.signIn(email, password) },
            onToggleCard = { navController.navigate(Route.REGISTER) }
        )
    }
}
