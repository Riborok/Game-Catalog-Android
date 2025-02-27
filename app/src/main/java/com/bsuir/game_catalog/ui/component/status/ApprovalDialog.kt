package com.bsuir.game_catalog.ui.component.status

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.bsuir.game_catalog.R

@Composable
fun ApprovalDialog(msgId: Int, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.approval)) },
        text = { Text(stringResource(msgId)) },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.ok_button))
            }
        },
    )
}
