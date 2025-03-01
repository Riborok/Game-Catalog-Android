package com.bsuir.game_catalog.ui.component.game.list.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bsuir.game_catalog.R

@Composable
fun ImageCard(
    imgUrl: String?,
    isFavorite: Boolean
) {
    Box(modifier = Modifier.padding(16.dp)) {
        AsyncImage(
            model = imgUrl,
            contentDescription = stringResource(R.string.game_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        if (isFavorite) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = stringResource(R.string.favorite),
                tint = Color.Red,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(20.dp)
                    .background(
                        Color.White.copy(alpha = 0.7f),
                        shape = CircleShape
                    )
                    .padding(2.dp)
            )
        }
    }
}