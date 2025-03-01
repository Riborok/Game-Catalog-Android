package com.bsuir.game_catalog.ui.screen.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.bsuir.game_catalog.ui.component.auth.AuthBackground
import com.bsuir.game_catalog.ui.component.auth.card.LoginCard
import com.bsuir.game_catalog.ui.component.error.ErrorNotification
import com.bsuir.game_catalog.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    onNavToRegister: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    AuthBackground {
        LoginCard(
            email = email,
            onEmailChange = { email = it },
            password = password,
            onPasswordChange = { password = it },
            onSignIn = { authViewModel.signIn(email, password) },
            onToggleCard = onNavToRegister
        )
        ErrorNotification(authViewModel)
    }
}
