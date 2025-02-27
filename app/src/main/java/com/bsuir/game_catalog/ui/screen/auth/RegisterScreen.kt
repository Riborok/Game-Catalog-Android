package com.bsuir.game_catalog.ui.screen.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.bsuir.game_catalog.ui.Background
import com.bsuir.game_catalog.ui.component.auth.card.RegisterCard
import com.bsuir.game_catalog.ui.component.error.ErrorNotification
import com.bsuir.game_catalog.utils.Route
import com.bsuir.game_catalog.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(
    authViewModel: AuthViewModel,
    navController: NavController,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Background {
        RegisterCard(
            email = email,
            onEmailChange = { email = it },
            password = password,
            onPasswordChange = { password = it },
            onSignUp = { authViewModel.signUp(email, password) },
            onToggleCard = { navController.navigate(Route.LOGIN) },
        )
        ErrorNotification(authViewModel)
    }
}
