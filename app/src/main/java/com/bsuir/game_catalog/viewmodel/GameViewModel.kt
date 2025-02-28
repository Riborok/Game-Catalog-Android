package com.bsuir.game_catalog.viewmodel

import android.app.Application
import com.bsuir.game_catalog.R
import com.bsuir.game_catalog.model.Game
import com.bsuir.game_catalog.repository.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel(application: Application) : ErrorHandlingViewModel(application) {
    private val repository = GameRepository()

    private val _games = MutableStateFlow<List<Game>>(emptyList())
    val games = _games.asStateFlow()

    init {
        loadGames()
    }

    private fun loadGames() {
        repository.getAllGames { result ->
            onResult(result, R.string.games_load_failed)
        }
    }

    private fun onResult(result: Result<List<Game>>, errorStrId: Int) {
        result.onSuccess { _games.value = it }
        result.onFailure { throwable ->
            val context = getApplication<Application>().applicationContext
            _errorMessage.value = throwable.message ?: context.getString(errorStrId)
        }
    }
}
