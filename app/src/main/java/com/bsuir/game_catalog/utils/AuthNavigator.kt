package com.bsuir.game_catalog.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.bsuir.game_catalog.viewmodel.AuthViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NavigateToMainIfAuthenticated(authViewModel: AuthViewModel, navController: NavController) {
    LaunchedEffect(Unit) {
        authViewModel.user.collectLatest { user ->
            if (user != null) {
                navController.navigate(AuthRoute.MAIN) {
                    popUpTo(AuthRoute.LOGIN) { inclusive = true }
                }
            }
        }
    }
}

@Composable
fun NavigateToLoginIfUnauthenticated(authViewModel: AuthViewModel, navController: NavController) {
    LaunchedEffect(Unit) {
        authViewModel.user.collectLatest { user ->
            if (user == null) {
                navController.navigate(AuthRoute.LOGIN) {
                    popUpTo(AuthRoute.MAIN) { inclusive = true }
                }
            }
        }
    }
}
