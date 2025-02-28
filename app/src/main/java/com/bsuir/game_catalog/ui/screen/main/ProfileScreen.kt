package com.bsuir.game_catalog.ui.screen.main

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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

@Composable
fun ProfileScreen(
    authViewModel: AuthViewModel,
    profileViewModel: ProfileViewModel
) {
    val remoteUserProfile by profileViewModel.userProfile.collectAsState()
    var localUserProfile by remember { mutableStateOf(remoteUserProfile) }
    LaunchedEffect(remoteUserProfile) {
        localUserProfile = remoteUserProfile
    }
    val onLocalUserProfileChange = { newUserProfile: UserProfile ->
        localUserProfile = newUserProfile
    }

    ProfileBackground {
        ProfileCard(
            userProfile = localUserProfile,
            onUserProfileChange = onLocalUserProfileChange
        )
        Spacer(modifier = Modifier.height(16.dp))
        DataCard(
            userProfile = localUserProfile,
            onUserProfileChange = onLocalUserProfileChange,
            onSaveProfile = { profileViewModel.saveUserProfile(localUserProfile) },
            onSignOut = { authViewModel.signOut() },
            onDeleteAccount = { authViewModel.deleteAccount() }
        )
        ErrorNotification(authViewModel)
        StatusNotification(R.string.profile_updated_successfully, profileViewModel)
    }
}