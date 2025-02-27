package com.bsuir.game_catalog.ui.component.profile.field

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bsuir.game_catalog.R
import com.bsuir.game_catalog.model.UserProfile
import com.bsuir.game_catalog.viewmodel.AuthViewModel
import com.bsuir.game_catalog.viewmodel.ProfileViewModel

@Composable
fun ProfileActions(
    authViewModel: AuthViewModel,
    profileViewModel: ProfileViewModel,
    userProfile: UserProfile,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(0.65f),
            onClick = {
                profileViewModel.saveUserProfile(userProfile)
            }) {
            Text(stringResource(R.string.save))
        }
        Button(modifier = Modifier.fillMaxWidth(0.9f),
            onClick = { authViewModel.signOut() }) {
            Text(stringResource(R.string.sign_out))
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
    Button(
        onClick = { authViewModel.deleteAccount() },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.error),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(stringResource(R.string.delete_account),
            color = MaterialTheme.colorScheme.onError)
    }
}