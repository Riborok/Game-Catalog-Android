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
import com.bsuir.game_catalog.ui.screen.profile.ProfileScreen
import com.bsuir.game_catalog.ui.theme.GameCatalogTheme
import com.bsuir.game_catalog.viewmodel.AuthViewModel
import com.bsuir.game_catalog.viewmodel.ProfileViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameCatalogTheme {
                val authViewModel = remember { getAndroidViewModel<AuthViewModel>(this, application) }
                val profileViewModel = remember { getAndroidViewModel<ProfileViewModel>(this, application) }

                val navController = rememberNavController()
                val initialDestination = remember { getInitialDest(authViewModel) }

                NavHost(
                    navController = navController,
                    startDestination = initialDestination
                ) {
                    composable(Routes.LOGIN) {
                        NavigateToProfileIfAuthenticated(authViewModel, navController)
                        LoginScreen(
                            authViewModel = authViewModel,
                            navController = navController,
                        )
                    }
                    composable(Routes.REGISTER) {
                        NavigateToProfileIfAuthenticated(authViewModel, navController)
                        RegisterScreen(
                            authViewModel = authViewModel,
                            navController = navController,
                        )
                    }
                    composable(Routes.PROFILE) {
                        NavigateToLoginIfUnauthenticated(authViewModel, navController)
                        ProfileScreen(
                            authViewModel = authViewModel,
                            profileViewModel = profileViewModel,
                        )
                    }
                }
            }
        }
    }
}

inline fun <reified T : ViewModel> getAndroidViewModel(
    owner: ViewModelStoreOwner,
    application: Application
): T {
    return ViewModelProvider(
        owner,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    )[T::class.java]
}

fun getInitialDest(authViewModel: AuthViewModel): String =
    if (authViewModel.user.value != null) Routes.PROFILE else Routes.LOGIN
