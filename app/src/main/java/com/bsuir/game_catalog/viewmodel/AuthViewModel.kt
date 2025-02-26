package com.bsuir.game_catalog.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.bsuir.game_catalog.R
import com.bsuir.game_catalog.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = AuthRepository()

    private val _user = MutableStateFlow(repository.currentUser)
    val user = _user.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    fun signIn(email: String, password: String) {
        repository.signIn(email, password) { onResult(it, R.string.sign_in_failed) }
    }

    fun signUp(email: String, password: String) {
        repository.signUp(email, password) { onResult(it, R.string.sign_up_failed) }
    }

    fun deleteAccount() {
        repository.deleteAccount { onResult(it, R.string.user_deletion_failed) }
    }

    private fun onResult(result: Result<FirebaseUser?>, errorStrId: Int) {
        result.onSuccess { firebaseUser ->
            _user.value = firebaseUser
            _errorMessage.value = null
        }
        result.onFailure { throwable ->
            val context = getApplication<Application>().applicationContext
            _errorMessage.value = throwable.message ?: context.getString(errorStrId)
        }
    }

    fun signOut() {
        repository.signOut()
        _user.value = null
        _errorMessage.value = null
    }
}
