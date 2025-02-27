package com.bsuir.game_catalog.ui.component.profile.avatar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun EditableAvatar(
    url: String,
    onUrlChange: (String) -> Unit
) {
    var showUrlDialog by remember { mutableStateOf(false) }

    ClickableAvatar(avatarUrl = url) {
        showUrlDialog = true
    }

    if (showUrlDialog) {
        AvatarUrlDialog(
            currentUrl = url,
            onDismiss = { showUrlDialog = false },
            onConfirm = { newUrl ->
                onUrlChange(newUrl)
                showUrlDialog = false
            }
        )
    }
}