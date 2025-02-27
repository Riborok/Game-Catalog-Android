package com.bsuir.game_catalog.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bsuir.game_catalog.R
import com.bsuir.game_catalog.model.UserProfile
import com.bsuir.game_catalog.ui.component.error.ErrorNotification
import com.bsuir.game_catalog.ui.component.profile.ProfileBackground
import com.bsuir.game_catalog.ui.component.profile.card.DataCard
import com.bsuir.game_catalog.ui.component.profile.card.ProfileCard
import com.bsuir.game_catalog.ui.component.status.StatusNotification
import com.bsuir.game_catalog.viewmodel.AuthViewModel
import com.bsuir.game_catalog.viewmodel.ProfileViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProfileScreen(
    authViewModel: AuthViewModel,
    profileViewModel: ProfileViewModel
) {
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

    ProfileBackground {
        ProfileCard(
            userProfile = userProfile,
            onUserProfileChange = onUserProfileChange
        )
        Spacer(modifier = Modifier.height(16.dp))
        DataCard(
            userProfile = userProfile,
            onUserProfileChange = onUserProfileChange,
            onSaveProfile = { profileViewModel.saveUserProfile(userProfile) },
            onSignOut = { authViewModel.signOut() },
            onDeleteAccount = { authViewModel.deleteAccount() }
        )
        ErrorNotification(authViewModel)
        StatusNotification(R.string.profile_updated_successfully, profileViewModel)
    }
}