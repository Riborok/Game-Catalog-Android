package com.bsuir.game_catalog

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
                navController.navigate(Routes.MAIN) {
                    popUpTo(Routes.AUTH) { inclusive = true }
                }
            }
        }
    }
}

@Composable
fun NavigateToAuthIfUnauthenticated(authViewModel: AuthViewModel, navController: NavController) {
    LaunchedEffect(Unit) {
        authViewModel.user.collectLatest { user ->
            if (user == null) {
                navController.navigate(Routes.AUTH) {
                    popUpTo(Routes.MAIN) { inclusive = true }
                }
            }
        }
    }
}
