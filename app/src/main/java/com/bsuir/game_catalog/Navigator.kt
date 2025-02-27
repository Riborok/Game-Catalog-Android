package com.bsuir.game_catalog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.bsuir.game_catalog.viewmodel.AuthViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NavigateToProfileIfAuthenticated(authViewModel: AuthViewModel, navController: NavController) {
    LaunchedEffect(Unit) {
        authViewModel.user.collectLatest { user ->
            if (user != null) {
                navController.navigate(Routes.PROFILE) {
                    popUpTo(Routes.LOGIN) { inclusive = true }
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
                navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.PROFILE) { inclusive = true }
                }
            }
        }
    }
}
