package com.bsuir.game_catalog.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.bsuir.game_catalog.R
import com.bsuir.game_catalog.repository.FavoriteRepository
import com.bsuir.game_catalog.utils.AuthStateTracker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : ErrorHandlingViewModel(application) {
    private val repository = FavoriteRepository()

    private val _favoriteGames = MutableStateFlow<List<String>>(emptyList())
    val favoriteGames = _favoriteGames.asStateFlow()

    init {
        observeFavoriteGamesState()
    }

    private fun observeFavoriteGamesState() {
        viewModelScope.launch {
            AuthStateTracker.observeAuthState().collect { firebaseUser ->
                if (firebaseUser != null) {
                    loadFavorites()
                } else {
                    _favoriteGames.value = emptyList()
                }
            }
        }
    }

    private fun loadFavorites() {
        repository.getFavorites { result ->
            onResult(result, R.string.favorites_load_failed)
        }
    }

    fun addGameToFavorites(gameId: String) {
        repository.addGameToFavorites(gameId) { result ->
            onResult(result, R.string.favorite_add_failed)
        }
    }

    fun removeGameFromFavorites(gameId: String) {
        repository.removeGameFromFavorites(gameId) { result ->
            onResult(result, R.string.favorite_remove_failed)
        }
    }

    private fun onResult(result: Result<List<String>>, errorStrId: Int) {
        result.onSuccess { _favoriteGames.value = it }
        result.onFailure { throwable ->
            val context = getApplication<Application>().applicationContext
            _errorMessage.value = throwable.message ?: context.getString(errorStrId)
        }
    }
}