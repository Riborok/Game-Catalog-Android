package com.bsuir.game_catalog.ui.component.game.selected.game_info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bsuir.game_catalog.R
import com.bsuir.game_catalog.model.Game

@Composable
fun GameGallery(game: Game) {
    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex =
        (Int.MAX_VALUE / 2) - ((Int.MAX_VALUE / 2) % game.imageUrls.size)
    )
    LazyRow(
        state = listState,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(Int.MAX_VALUE) { index ->
            val remainder = index % game.imageUrls.size
            val imageUrl = game.imageUrls[remainder]
            val extraStartPadding = if (remainder == 0) 8.dp else 0.dp
            val extraEndPadding = if (remainder == game.imageUrls.size - 1) 16.dp else 0.dp

            Card(
                modifier = Modifier
                    .padding(start = extraStartPadding, end = extraEndPadding)
                    .size(180.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .shadow(6.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = stringResource(R.string.game_image),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}