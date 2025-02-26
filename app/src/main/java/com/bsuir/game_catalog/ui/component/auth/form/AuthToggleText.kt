package com.bsuir.game_catalog.ui.component.auth.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AuthToggleText(toggleText: String, toggleButtonText: String, onToggleClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = toggleText, color = MaterialTheme.colorScheme.onSurface)
        TextButton(onClick = onToggleClick) {
            Text(text = toggleButtonText, color = MaterialTheme.colorScheme.primary)
        }
    }
}