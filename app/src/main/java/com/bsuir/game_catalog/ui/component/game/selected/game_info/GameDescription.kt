package com.bsuir.game_catalog.ui.component.game.selected.game_info

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.bsuir.game_catalog.R
import com.bsuir.game_catalog.model.Game

@Composable
fun GameDescription(game: Game) {
    val context = LocalContext.current
    Text(text = "${stringResource(R.string.description)}:",
        style = MaterialTheme.typography.titleMedium)
    Text(text = game.description,
        style = MaterialTheme.typography.bodyMedium)
    Text(text = "${stringResource(R.string.company)}: ${game.company}",
        style = MaterialTheme.typography.bodyMedium)
    Text(text = "${stringResource(R.string.release_date)}: ${game.releaseDate}",
        style = MaterialTheme.typography.bodyMedium)
    Text(
        text = stringResource(R.string.official_website),
        modifier = Modifier.clickable {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(game.officialWebsite))
            context.startActivity(intent)
        }
    )
}