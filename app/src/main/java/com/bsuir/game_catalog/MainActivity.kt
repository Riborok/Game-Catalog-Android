package com.bsuir.game_catalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bsuir.game_catalog.ui.screen.auth.LoginScreen
import com.bsuir.game_catalog.ui.screen.auth.RegisterScreen
import com.bsuir.game_catalog.ui.screen.main.MainScreen
import com.bsuir.game_catalog.ui.theme.ColoredBackground
import com.bsuir.game_catalog.ui.theme.GameCatalogTheme
import com.bsuir.game_catalog.utils.AuthRoute
import com.bsuir.game_catalog.utils.NavigateToLoginIfUnauthenticated
import com.bsuir.game_catalog.utils.NavigateToMainIfAuthenticated
import com.bsuir.game_catalog.utils.getAndroidViewModel
import com.bsuir.game_catalog.viewmodel.AuthViewModel
import com.bsuir.game_catalog.viewmodel.FavoriteViewModel
import com.bsuir.game_catalog.viewmodel.GameViewModel
import com.bsuir.game_catalog.viewmodel.ProfileViewModel
import com.bsuir.game_catalog.viewmodel.ReviewViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameCatalogTheme {
                ColoredBackground {
                    val authViewModel =
                        remember { getAndroidViewModel<AuthViewModel>(application) }
                    val profileViewModel =
                        remember { getAndroidViewModel<ProfileViewModel>(application) }
                    val gameViewModel =
                        remember { getAndroidViewModel<GameViewModel>(application) }
                    val favoriteViewModel =
                        remember { getAndroidViewModel<FavoriteViewModel>(application) }
                    val reviewViewModel =
                        remember { getAndroidViewModel<ReviewViewModel>(application) }

                    val navController = rememberNavController()
                    val initialDestination = remember { AuthRoute.getInitialRoute(authViewModel) }

                    NavHost(
                        navController = navController,
                        startDestination = initialDestination
                    ) {
                        composable(AuthRoute.LOGIN) {
                            NavigateToMainIfAuthenticated(authViewModel, navController)
                            LoginScreen(
                                authViewModel = authViewModel,
                                onNavToRegister = { navController.navigate(AuthRoute.REGISTER) },
                            )
                        }
                        composable(AuthRoute.REGISTER) {
                            NavigateToMainIfAuthenticated(authViewModel, navController)
                            RegisterScreen(
                                authViewModel = authViewModel,
                                onNavToLogin = { navController.navigate(AuthRoute.LOGIN) },
                            )
                        }
                        composable(AuthRoute.MAIN) {
                            NavigateToLoginIfUnauthenticated(authViewModel, navController)
                            MainScreen(
                                authViewModel = authViewModel,
                                profileViewModel = profileViewModel,
                                gameViewModel = gameViewModel,
                                favoriteViewModel = favoriteViewModel,
                                reviewViewModel = reviewViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}
