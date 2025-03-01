package com.bsuir.game_catalog.ui.component.game.selected.game_info

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bsuir.game_catalog.model.Game

@Composable
fun GameTitle(game: Game) {
    Text(
        text = game.title,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(bottom = 10.dp)
    )
}