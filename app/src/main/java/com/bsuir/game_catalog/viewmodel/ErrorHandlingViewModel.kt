package com.bsuir.game_catalog.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class ErrorHandlingViewModel(application: Application) : AndroidViewModel(application) {
    protected val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    fun resetError() {
        _errorMessage.value = null
    }
}