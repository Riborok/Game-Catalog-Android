package com.bsuir.game_catalog.ui.screen.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bsuir.game_catalog.ui.component.nav.BottomNavigationBar
import com.bsuir.game_catalog.utils.MainRoute
import com.bsuir.game_catalog.viewmodel.AuthViewModel
import com.bsuir.game_catalog.viewmodel.FavoriteViewModel
import com.bsuir.game_catalog.viewmodel.GameViewModel
import com.bsuir.game_catalog.viewmodel.ProfileViewModel
import com.bsuir.game_catalog.viewmodel.ReviewViewModel

@Composable
fun MainScreen(
    authViewModel: AuthViewModel,
    profileViewModel: ProfileViewModel,
    gameViewModel: GameViewModel,
    favoriteViewModel: FavoriteViewModel,
    reviewViewModel: ReviewViewModel,
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
                val games by gameViewModel.games.collectAsState()
                val favorites by favoriteViewModel.favoriteGames.collectAsState()
                GameListScreen(
                    games = games,
                    favorites = favorites,
                    onClickOnGame = { gameId: String ->
                        navController.navigate("${MainRoute.SelectedGame.ROUTE}/${gameId}")
                    }
                )
            }
            composable(MainRoute.FAVORITES) {
                val games by gameViewModel.games.collectAsState()
                val favorites by favoriteViewModel.favoriteGames.collectAsState()
                GameListScreen(
                    games = games.filter { it.id in favorites },
                    favorites = favorites,
                    onClickOnGame = { gameId: String ->
                        navController.navigate("${MainRoute.SelectedGame.ROUTE}/${gameId}")
                    }
                )
            }
            composable("${MainRoute.SelectedGame.ROUTE}/{${MainRoute.SelectedGame.ID}}",
                arguments = listOf(
                    navArgument(MainRoute.SelectedGame.ID) { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val gameId = backStackEntry.arguments?.getString(MainRoute.SelectedGame.ID)!!
                SelectedGameScreen(
                    authViewModel = authViewModel,
                    gameViewModel = gameViewModel,
                    favoriteViewModel = favoriteViewModel,
                    reviewViewModel = reviewViewModel,
                    gameId = gameId,
                    onBackClick = { navController.popBackStack() },
                )
            }
        }
    }
}
