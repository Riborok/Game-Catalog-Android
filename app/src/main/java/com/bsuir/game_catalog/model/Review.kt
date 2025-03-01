package com.bsuir.game_catalog.model

data class ReviewRequest(
    var userId: String = "",
    var gameId: String = "",
    var rating: Int = 0,
    var text: String = ""
)

data class Review(
    var user: UserProfile = UserProfile(),
    var gameId: String = "",
    var rating: Int = 0,
    var text: String = ""
)
