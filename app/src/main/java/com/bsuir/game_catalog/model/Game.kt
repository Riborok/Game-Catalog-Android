package com.bsuir.game_catalog.model

data class Game(
    var id: String = "",
    var title: String = "",
    var description: String = "",
    var company: String = "",
    var releaseDate: String = "",
    var officialWebsite: String = "",
    var imageUrls: List<String> = emptyList(),
)
