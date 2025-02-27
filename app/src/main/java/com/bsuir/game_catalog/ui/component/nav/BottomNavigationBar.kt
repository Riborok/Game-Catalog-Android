package com.bsuir.game_catalog.ui.component.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.bsuir.game_catalog.R
import com.bsuir.game_catalog.utils.MainRoute

@Composable
fun BottomNavigationBar(navController: NavController, currentRoute: String?) {
    NavigationBar {
        NavigationItem(
            icon = Icons.Default.Person,
            contentDescription = stringResource(R.string.user_profile),
            route = MainRoute.PROFILE,
            currentRoute = currentRoute,
            navController = navController
        )
        NavigationItem(
            icon = Icons.AutoMirrored.Filled.List,
            contentDescription = stringResource(R.string.games),
            route = MainRoute.GAME_LIST,
            currentRoute = currentRoute,
            navController = navController
        )
        NavigationItem(
            icon = Icons.Default.Favorite,
            contentDescription = stringResource(R.string.favorite),
            route = MainRoute.FAVORITES,
            currentRoute = currentRoute,
            navController = navController
        )
    }
}
