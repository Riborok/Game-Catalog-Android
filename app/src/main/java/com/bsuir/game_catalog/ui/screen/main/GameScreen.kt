package com.bsuir.game_catalog.ui.screen.main

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bsuir.game_catalog.model.ReviewRequest
import com.bsuir.game_catalog.ui.component.game.selected.GameBackground
import com.bsuir.game_catalog.ui.component.game.selected.HeaderImage
import com.bsuir.game_catalog.ui.component.game.selected.game_info.GameInfo
import com.bsuir.game_catalog.ui.component.game.selected.review.ReviewInputForm
import com.bsuir.game_catalog.ui.component.game.selected.review.ReviewsSection
import com.bsuir.game_catalog.viewmodel.AuthViewModel
import com.bsuir.game_catalog.viewmodel.FavoriteViewModel
import com.bsuir.game_catalog.viewmodel.GameViewModel
import com.bsuir.game_catalog.viewmodel.ReviewViewModel

@Composable
fun GameScreen(
    authViewModel: AuthViewModel,
    gameViewModel: GameViewModel,
    favoriteViewModel: FavoriteViewModel,
    reviewViewModel: ReviewViewModel,
    gameId: String,
    onBackClick: () -> Unit
) {
    val games by gameViewModel.games.collectAsState()
    val favorites by favoriteViewModel.favoriteGames.collectAsState()
    val user by authViewModel.user.collectAsState()
    val reviews by reviewViewModel.reviews.collectAsState()

    val game = games.find { it.id == gameId }!!
    LaunchedEffect(Unit) { reviewViewModel.loadReviews(game.id) }
    GameBackground(
        header = {
            val isFavorite = favorites.contains(gameId)
            HeaderImage(game, isFavorite, onBackClick, onFavoriteClick = {
                if (isFavorite) {
                    favoriteViewModel.removeGameFromFavorites(gameId)
                } else {
                    favoriteViewModel.addGameToFavorites(gameId)
                }
            })
        },
        content = {
            GameInfo(game)
            Spacer(modifier = Modifier.height(16.dp))
            ReviewsSection(reviews)
            Spacer(modifier = Modifier.height(8.dp))
            ReviewInputForm( onSubmitted = { comment, rating ->
                val uid = user?.uid
                if (uid != null) {
                    val request = ReviewRequest(
                        userId = uid,
                        gameId = game.id,
                        rating = rating,
                        text = comment
                    )
                    reviewViewModel.createReview(request)
                }
            })
        }
    )
}
