package com.bsuir.game_catalog.ui.component.profile.field

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bsuir.game_catalog.R

@Composable
fun ProfileActions(
    onSaveProfile: () -> Unit,
    onSignOut: () -> Unit,
    onDeleteAccount: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            modifier = Modifier.weight(1.5f),
            onClick = onSaveProfile
        ) {
            Text(stringResource(R.string.save))
        }
        Spacer(modifier = Modifier.weight(0.1f))
        Button(modifier = Modifier.weight(1f),
            onClick = onSignOut
        ) {
            Text(stringResource(R.string.sign_out))
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
    Button(
        onClick = onDeleteAccount,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.error),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(stringResource(R.string.delete_account),
            color = MaterialTheme.colorScheme.onError)
    }
}