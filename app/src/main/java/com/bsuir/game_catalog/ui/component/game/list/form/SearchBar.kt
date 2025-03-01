package com.bsuir.game_catalog.ui.component.game.list.form

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bsuir.game_catalog.R

@Composable
fun SearchBar(searchQuery: String, onSearchChange: (String) -> Unit) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchChange,
        label = { Text(stringResource(R.string.search)) },
        leadingIcon = { Icon(Icons.Default.Search,
            contentDescription = stringResource(R.string.search_game))
        },
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    )
}