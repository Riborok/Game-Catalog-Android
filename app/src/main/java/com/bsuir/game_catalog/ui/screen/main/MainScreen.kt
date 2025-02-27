package com.bsuir.game_catalog.ui.screen.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bsuir.game_catalog.ui.component.nav.BottomNavigationBar
import com.bsuir.game_catalog.utils.MainRoute
import com.bsuir.game_catalog.viewmodel.AuthViewModel
import com.bsuir.game_catalog.viewmodel.ProfileViewModel

@Composable
fun MainScreen(
    authViewModel: AuthViewModel,
    profileViewModel: ProfileViewModel
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = { BottomNavigationBar(
            navController = navController,
            currentRoute = currentRoute
        ) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MainRoute.PROFILE,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(MainRoute.PROFILE) {
                ProfileScreen(
                    authViewModel = authViewModel,
                    profileViewModel = profileViewModel
                )
            }
            composable(MainRoute.GAME_LIST) {
                GameListScreen()
            }
            composable(MainRoute.FAVORITES) {
                FavoritesScreen()
            }
        }
    }
}
