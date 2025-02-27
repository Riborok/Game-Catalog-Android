package com.bsuir.game_catalog.ui.component.error

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.bsuir.game_catalog.viewmodel.ErrorHandlingViewModel

@Composable
fun ErrorNotification(errorHandlingViewModel: ErrorHandlingViewModel) {
    val errorMessage by errorHandlingViewModel.errorMessage.collectAsState()

    errorMessage?.let {
        ErrorDialog(
            errorMessage = it,
            onDismiss = errorHandlingViewModel::resetError
        )
    }
}