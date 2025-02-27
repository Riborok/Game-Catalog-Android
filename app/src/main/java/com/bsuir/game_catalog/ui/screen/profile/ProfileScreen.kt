package com.bsuir.game_catalog.ui.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bsuir.game_catalog.Routes
import com.bsuir.game_catalog.model.UserProfile
import com.bsuir.game_catalog.ui.Background
import com.bsuir.game_catalog.ui.component.auth.card.LoginCard
import com.bsuir.game_catalog.ui.component.profile.card.DataCard
import com.bsuir.game_catalog.ui.component.profile.card.ProfileCard
import com.bsuir.game_catalog.viewmodel.AuthViewModel
import com.bsuir.game_catalog.viewmodel.ProfileViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProfileScreen(
    authViewModel: AuthViewModel,
    profileViewModel: ProfileViewModel,
    navController: NavController
) {
    LaunchedEffect(Unit) {
        authViewModel.user.collectLatest { user ->
            if (user == null) {
                navController.navigate(Routes.AUTH) {
                    popUpTo(Routes.MAIN) { inclusive = true }
                }
            }
        }
    }

    var userProfile by remember { mutableStateOf(profileViewModel.userProfile.value) }
    val onUserProfileChange = { newUserProfile: UserProfile ->
        userProfile = newUserProfile
    }

    LaunchedEffect(Unit) {
        profileViewModel.userProfile.collectLatest { newUserProfile ->
            if (userProfile != newUserProfile) {
                userProfile = newUserProfile
            }
        }
    }

    Background {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            ProfileCard(
                userProfile = userProfile,
                onUserProfileChange = onUserProfileChange
            )
            Spacer(modifier = Modifier.height(16.dp))
            DataCard(
                authViewModel = authViewModel,
                profileViewModel = profileViewModel,
                userProfile = userProfile,
                onUserProfileChange = onUserProfileChange
            )
        }
    }
}