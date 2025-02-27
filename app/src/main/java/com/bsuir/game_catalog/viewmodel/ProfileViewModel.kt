package com.bsuir.game_catalog.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.bsuir.game_catalog.R
import com.bsuir.game_catalog.model.UserProfile
import com.bsuir.game_catalog.repository.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : StatusHandlingViewModel(application) {
    private val repository = ProfileRepository()

    private val _userProfile = MutableStateFlow(UserProfile())
    val userProfile = _userProfile.asStateFlow()

    init {
        observeUserAuthState()
    }

    private fun observeUserAuthState() {
        viewModelScope.launch {
            repository.observeAuthState().collect { firebaseUser ->
                if (firebaseUser != null) {
                    loadUserProfile()
                } else {
                    _userProfile.value = UserProfile()
                }
            }
        }
    }

    private fun loadUserProfile() {
        repository.getUserProfile { result -> onResult(result, R.string.profile_load_failed) }
    }

    fun saveUserProfile(profile: UserProfile) {
        repository.saveUserProfile(profile) { result ->
            result.onSuccess { _isApproved.value = true }
            onResult(result, R.string.profile_save_failed)
        }
    }

    private fun onResult(result: Result<*>, errorStrId: Int) {
        result.onSuccess { data ->
            if (data is UserProfile) {
                _userProfile.value = data
            }
        }
        result.onFailure { throwable ->
            val context = getApplication<Application>().applicationContext
            _errorMessage.value = throwable.message ?: context.getString(errorStrId)
        }
    }
}
