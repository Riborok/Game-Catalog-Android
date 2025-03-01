package com.bsuir.game_catalog.ui.component.game.selected.game_info

import androidx.compose.runtime.Composable
import com.bsuir.game_catalog.model.Game

@Composable
fun GameInfo(game: Game) {
    GameTitle(game)
    GameGallery(game)
    GameDescription(game)
}