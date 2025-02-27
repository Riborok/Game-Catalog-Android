package com.bsuir.game_catalog

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bsuir.game_catalog.ui.screen.auth.LoginScreen
import com.bsuir.game_catalog.ui.screen.auth.RegisterScreen
import com.bsuir.game_catalog.ui.screen.main.MainScreen
import com.bsuir.game_catalog.ui.theme.GameCatalogTheme
import com.bsuir.game_catalog.utils.AuthRoute
import com.bsuir.game_catalog.utils.NavigateToLoginIfUnauthenticated
import com.bsuir.game_catalog.utils.NavigateToMainIfAuthenticated
import com.bsuir.game_catalog.viewmodel.AuthViewModel
import com.bsuir.game_catalog.viewmodel.ProfileViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameCatalogTheme {
                val authViewModel = remember { getAndroidViewModel<AuthViewModel>(application) }
                val profileViewModel = remember { getAndroidViewModel<ProfileViewModel>(application) }

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
                            navController = navController,
                        )
                    }
                    composable(AuthRoute.REGISTER) {
                        NavigateToMainIfAuthenticated(authViewModel, navController)
                        RegisterScreen(
                            authViewModel = authViewModel,
                            navController = navController,
                        )
                    }
                    composable(AuthRoute.MAIN) {
                        NavigateToLoginIfUnauthenticated(authViewModel, navController)
                        MainScreen(
                            authViewModel = authViewModel,
                            profileViewModel = profileViewModel,
                        )
                    }
                }
            }
        }
    }
}

inline fun <reified T : ViewModel> ViewModelStoreOwner.getAndroidViewModel(
    application: Application
): T {
    return ViewModelProvider(
        this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    )[T::class.java]
}
