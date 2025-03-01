package com.bsuir.game_catalog.ui.component.game.list.utils

import com.bsuir.game_catalog.model.Game
import com.bsuir.game_catalog.utils.parseDate

fun filterGames(games: List<Game>, searchQuery: String, selectedCompany: String?): List<Game> {
    return games.filter { game ->
        val matchesSearch = searchQuery.isBlank()
                || game.title.contains(searchQuery, ignoreCase = true)
        val matchesCompany = selectedCompany?.let { game.company == it } ?: true
        matchesSearch && matchesCompany
    }
}

fun sortGames(games: List<Game>, sortOption: SortOption): List<Game> {
    return when (sortOption) {
        SortOption.TITLE -> games.sortedBy { it.title.lowercase() }
        SortOption.COMPANY -> games.sortedBy { it.company.lowercase() }
        SortOption.RELEASE_DATE -> games.sortedByDescending {
            parseDate(it.releaseDate)?.time ?: Long.MAX_VALUE
        }
    }
}