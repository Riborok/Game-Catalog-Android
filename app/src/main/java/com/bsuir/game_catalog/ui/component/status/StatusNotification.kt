package com.bsuir.game_catalog.ui.component.status

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.bsuir.game_catalog.ui.component.error.ErrorNotification
import com.bsuir.game_catalog.viewmodel.StatusHandlingViewModel

@Composable
fun StatusNotification(msgId: Int, statusHandlingViewModel: StatusHandlingViewModel) {
    ErrorNotification(statusHandlingViewModel)

    val isApproved by statusHandlingViewModel.isApproved.collectAsState()
    if (isApproved) {
        ApprovalDialog(
            msgId = msgId,
            onDismiss = statusHandlingViewModel::resetApproval
        )
    }
}