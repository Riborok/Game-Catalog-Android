package com.bsuir.game_catalog.ui.component.auth.card

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.bsuir.game_catalog.R

@Composable
fun LoginCard(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    errorMessage: String?,
    onSignIn: () -> Unit,
    onToggleCard: () -> Unit
) {
    AuthCard(
        title = stringResource(R.string.sign_in_title),
        email = email,
        onEmailChange = onEmailChange,
        password = password,
        onPasswordChange = onPasswordChange,
        errorMessage = errorMessage,
        buttonText = stringResource(R.string.sign_in_button),
        onButtonClick = onSignIn,
        toggleText = stringResource(R.string.sign_up_toggle),
        toggleButtonText = stringResource(R.string.sign_up_button),
        onToggleClick = onToggleCard
    )
}
