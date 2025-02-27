package com.bsuir.game_catalog.viewmodel

import android.app.Application
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class StatusHandlingViewModel(application: Application): ErrorHandlingViewModel(application) {
    protected val _isApproved = MutableStateFlow<Boolean>(false)
    val isApproved = _isApproved.asStateFlow()

    fun resetApproval() {
        _isApproved.value = false
    }
}