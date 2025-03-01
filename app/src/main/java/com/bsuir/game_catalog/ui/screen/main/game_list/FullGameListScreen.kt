package com.bsuir.game_catalog.ui.screen.main.game_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.bsuir.game_catalog.viewmodel.FavoriteViewModel
import com.bsuir.game_catalog.viewmodel.GameViewModel

@Composable
fun FullGameListScreen(
    gameViewModel: GameViewModel,
    favoriteViewModel: FavoriteViewModel,
    onClickOnGame: (String) -> Unit,
) {
    val games by gameViewModel.games.collectAsState()
    val favorites by favoriteViewModel.favoriteGames.collectAsState()
    GameListScreen(games, favorites, onClickOnGame)
}