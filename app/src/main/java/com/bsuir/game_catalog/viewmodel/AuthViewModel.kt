package com.bsuir.game_catalog.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bsuir.game_catalog.R
import com.bsuir.game_catalog.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = AuthRepository()

    private val _user = MutableLiveData(repository.currentUser)
    val user: LiveData<FirebaseUser?> = _user

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

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
            _user.postValue(firebaseUser)
            _errorMessage.postValue(null)
        }
        result.onFailure { throwable ->
            val context = getApplication<Application>().applicationContext
            _errorMessage.postValue(
                throwable.message ?: context.getString(errorStrId)
            )
        }
    }

    fun signOut() {
        repository.signOut()
        _user.value = null
        _errorMessage.value = null
    }
}
