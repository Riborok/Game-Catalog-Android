package com.bsuir.game_catalog.ui.component.profile.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import com.bsuir.game_catalog.R
import com.bsuir.game_catalog.model.UserProfile
import com.bsuir.game_catalog.ui.component.profile.avatar.EditableAvatar
import com.bsuir.game_catalog.ui.component.profile.field.ProfileActions
import com.bsuir.game_catalog.ui.component.profile.field.ProfileFields
import com.bsuir.game_catalog.viewmodel.AuthViewModel
import com.bsuir.game_catalog.viewmodel.ProfileViewModel

@Composable
fun DataCard(
    authViewModel: AuthViewModel,
    profileViewModel: ProfileViewModel,
    userProfile: UserProfile,
    onUserProfileChange: (UserProfile) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            ProfileFields(
                userProfile = userProfile,
                onUserProfileChange = onUserProfileChange
            )
            Spacer(modifier = Modifier.height(16.dp))
            ProfileActions(
                authViewModel = authViewModel,
                profileViewModel = profileViewModel,
                userProfile = userProfile
            )
        }
    }
}