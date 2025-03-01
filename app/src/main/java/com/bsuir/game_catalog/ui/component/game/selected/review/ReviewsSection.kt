package com.bsuir.game_catalog.ui.component.game.selected.review

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bsuir.game_catalog.R
import com.bsuir.game_catalog.model.Review

@Composable
fun ReviewsSection(reviews: List<Review>) {
    Text(text = stringResource(R.string.comments),
        style = MaterialTheme.typography.titleMedium)
    if (reviews.isEmpty()) {
        Text(text = stringResource(R.string.no_comments),
            style = MaterialTheme.typography.bodyMedium)
    } else {
        LazyColumn(
            modifier = Modifier.heightIn(max = 300.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(reviews) { review ->
                ReviewItem(review)
            }
        }
    }
}