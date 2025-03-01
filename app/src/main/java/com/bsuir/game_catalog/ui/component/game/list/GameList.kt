package com.bsuir.game_catalog.ui.component.game.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bsuir.game_catalog.model.Game
import com.bsuir.game_catalog.ui.component.game.list.card.GameCard

@Composable
fun GameList(games: List<Game>, favorites: List<String>, onClickOnGame: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(games) { game ->
            GameCard(game, favorites.contains(game.id), onClickOnGame)
        }
    }
}