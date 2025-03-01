package com.bsuir.game_catalog.ui.screen.main.game_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bsuir.game_catalog.model.Game
import com.bsuir.game_catalog.ui.component.game.list.GameList
import com.bsuir.game_catalog.ui.component.game.list.card.EmptyGameCard
import com.bsuir.game_catalog.ui.component.game.list.form.FilterRow
import com.bsuir.game_catalog.ui.component.game.list.form.SearchBar
import com.bsuir.game_catalog.ui.component.game.list.utils.SortOption
import com.bsuir.game_catalog.ui.component.game.list.utils.filterGames
import com.bsuir.game_catalog.ui.component.game.list.utils.sortGames

@Composable
fun GameListScreen(
    games: List<Game>,
    favorites: List<String>,
    onClickOnGame: (String) -> Unit,
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var selectedCompany by rememberSaveable { mutableStateOf<String?>(null) }
    var sortOption by rememberSaveable { mutableStateOf(SortOption.TITLE) }

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(searchQuery) { searchQuery = it }
        FilterRow(
            companies = games.map { it.company }.distinct(),
            selectedCompany = selectedCompany,
            onCompanySelected = { selectedCompany = it },
            sortOption = sortOption,
            onSortSelected = { sortOption = it }
        )
        if (games.isEmpty()) {
            EmptyGameCard()
        } else {
            val filteredGames = filterGames(games, searchQuery, selectedCompany)
            val sortedGames = sortGames(filteredGames, sortOption)
            GameList(sortedGames, favorites, onClickOnGame)
        }
    }
}
