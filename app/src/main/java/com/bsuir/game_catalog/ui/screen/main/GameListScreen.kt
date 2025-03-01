package com.bsuir.game_catalog.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bsuir.game_catalog.model.Game
import com.bsuir.game_catalog.ui.component.general.ClickableOutlinedTextField
import com.bsuir.game_catalog.utils.parseDate

enum class SortOption(val displayName: String) {
    TITLE("Title"),
    COMPANY("Company"),
    RELEASE_DATE("Release Date")
}

@Composable
fun GameListScreen(
    games: List<Game>,
    favorites: List<String>,
    onClickOnGame: (String) -> Unit,
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var selectedCompany by rememberSaveable { mutableStateOf<String?>(null) }
    var companyDropdownExpanded by remember { mutableStateOf(false) }

    var sortOption by rememberSaveable { mutableStateOf(SortOption.TITLE) }
    var sortDropdownExpanded by remember { mutableStateOf(false) }

    val companies = games.map { it.company }.distinct()

    val filteredGames = games.filter { game ->
        val matchesSearch = searchQuery.isBlank() ||
                game.title.contains(searchQuery, ignoreCase = true)
        val matchesCompany = selectedCompany?.let { game.company == it } ?: true
        matchesSearch && matchesCompany
    }

    val sortedGames = when (sortOption) {
        SortOption.TITLE -> filteredGames.sortedBy { it.title.lowercase() }
        SortOption.COMPANY -> filteredGames.sortedBy { it.company.lowercase() }
        SortOption.RELEASE_DATE ->  {
            filteredGames.sortedByDescending { parseDate(it.releaseDate)?.time ?: Long.MAX_VALUE }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search game") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1.2f)) {
                ClickableOutlinedTextField(
                    value = selectedCompany ?: "All companies",
                    onClick = { companyDropdownExpanded = true },
                    label = "Company",
                    trailingIcon = {
                        Icon(
                            imageVector = if (companyDropdownExpanded)
                                Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = "Dropdown"
                        )
                    }
                )
                DropdownMenu(
                    expanded = companyDropdownExpanded,
                    onDismissRequest = { companyDropdownExpanded = false },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .heightIn(max = 200.dp)
                ) {
                    DropdownMenuItem(
                        text = { Text("All companies") },
                        onClick = {
                            selectedCompany = null
                            companyDropdownExpanded = false
                        }
                    )
                    companies.forEach { company ->
                        DropdownMenuItem(
                            text = { Text(company) },
                            onClick = {
                                selectedCompany = company
                                companyDropdownExpanded = false
                            }
                        )
                    }
                }
            }

            Box(modifier = Modifier.weight(1f)) {
                ClickableOutlinedTextField(
                    value = sortOption.displayName,
                    onClick = { sortDropdownExpanded = true },
                    label = "Sort by",
                    trailingIcon = {
                        Icon(
                            imageVector = if (sortDropdownExpanded)
                                Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = "Dropdown"
                        )
                    }
                )
                DropdownMenu(
                    expanded = sortDropdownExpanded,
                    onDismissRequest = { sortDropdownExpanded = false },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .heightIn(max = 200.dp)
                ) {
                    SortOption.entries.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option.displayName) },
                            onClick = {
                                sortOption = option
                                sortDropdownExpanded = false
                            }
                        )
                    }
                }
            }
        }

        if (games.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(sortedGames) { game ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onClickOnGame(game.id) },
                        shape = RoundedCornerShape(8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Box(modifier = Modifier.padding(16.dp)) {
                                AsyncImage(
                                    model = game.imageUrls.firstOrNull(),
                                    contentDescription = "Game image",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(80.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                )
                                if (favorites.contains(game.id)) {
                                    Icon(
                                        imageVector = Icons.Filled.Favorite,
                                        contentDescription = "Favorite",
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
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = game.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                                Text(
                                    text = game.company,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                                )
                                Text(
                                    text = "Released: ${game.releaseDate}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
