package com.bsuir.game_catalog.ui.component.game.list.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bsuir.game_catalog.R
import com.bsuir.game_catalog.model.Game

@Composable
fun GameCard(game: Game, isFavorite: Boolean, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick(game.id) },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(4.dp)
        ) {
            ImageCard(game.imageUrls.firstOrNull(), isFavorite)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(game.title,
                    style = MaterialTheme.typography.titleMedium)
                Text(game.company,
                    style = MaterialTheme.typography.bodySmall.copy())
                Text("${stringResource(R.string.released)}: ${game.releaseDate}",
                    style = MaterialTheme.typography.bodySmall.copy())
            }
        }
    }
}