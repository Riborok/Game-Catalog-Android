package com.bsuir.game_catalog.ui.component.auth.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.bsuir.game_catalog.R
import com.bsuir.game_catalog.ui.component.auth.form.AuthButton
import com.bsuir.game_catalog.ui.component.auth.form.AuthTextField
import com.bsuir.game_catalog.ui.component.auth.form.AuthTitle
import com.bsuir.game_catalog.ui.component.auth.form.AuthToggleText
import com.bsuir.game_catalog.ui.component.general.ElevatedCard

@Composable
fun AuthCard(
    title: String,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    buttonText: String,
    onButtonClick: () -> Unit,
    toggleText: String,
    toggleButtonText: String,
    onToggleClick: () -> Unit,
) {
    val colors = MaterialTheme.colorScheme

    ElevatedCard {
        Column(
            modifier = Modifier
                .padding(32.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthTitle(title)
            Spacer(modifier = Modifier.height(24.dp))

            AuthTextField(
                value = email,
                onValueChange = onEmailChange,
                label = stringResource(R.string.email_label)
            )
            Spacer(modifier = Modifier.height(16.dp))

            AuthTextField(
                value = password,
                onValueChange = onPasswordChange,
                label = stringResource(R.string.password_label),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(24.dp))

            AuthButton(text = buttonText, onClick = onButtonClick)
            Spacer(modifier = Modifier.height(4.dp))

            AuthToggleText(toggleText, toggleButtonText, onToggleClick)
        }
    }
}