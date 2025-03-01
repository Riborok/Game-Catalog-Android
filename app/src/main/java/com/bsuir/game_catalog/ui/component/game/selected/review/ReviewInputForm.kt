package com.bsuir.game_catalog.ui.component.game.selected.review

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.bsuir.game_catalog.R

@Composable
fun ColumnScope.ReviewInputForm(
    onSubmitted: (comment: String, rating: Int) -> Unit
) {
    var commentText by rememberSaveable { mutableStateOf("") }
    var ratingText by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = commentText,
        onValueChange = { commentText = it },
        label = { Text(stringResource(R.string.add_comment)) },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = ratingText,
        onValueChange = {
            if (it.length <= 1 && (it.isEmpty() || it.toIntOrNull() in 1..5)) {
                ratingText = it
            }
        },
        label = { Text(stringResource(R.string.rating)) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
    Button(
        onClick = {
            val rating = ratingText.toIntOrNull()
            if (rating != null) {
                onSubmitted(commentText, rating)
                commentText = ""
                ratingText = ""
            }
        },
        modifier = Modifier.align(Alignment.End)
    ) {
        Text(stringResource(R.string.submit))
    }
}