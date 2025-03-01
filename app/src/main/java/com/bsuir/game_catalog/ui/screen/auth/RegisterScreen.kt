package com.bsuir.game_catalog.ui.screen.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.bsuir.game_catalog.ui.component.auth.AuthBackground
import com.bsuir.game_catalog.ui.component.auth.card.RegisterCard
import com.bsuir.game_catalog.ui.component.error.ErrorNotification
import com.bsuir.game_catalog.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(
    authViewModel: AuthViewModel,
    onNavToLogin: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    AuthBackground {
        RegisterCard(
            email = email,
            onEmailChange = { email = it },
            password = password,
            onPasswordChange = { password = it },
            onSignUp = { authViewModel.signUp(email, password) },
            onToggleCard = onNavToLogin
        )
        ErrorNotification(authViewModel)
    }
}
