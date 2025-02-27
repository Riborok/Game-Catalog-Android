package com.bsuir.game_catalog.ui.component.profile.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bsuir.game_catalog.model.UserProfile
import com.bsuir.game_catalog.ui.component.general.ElevatedCard
import com.bsuir.game_catalog.ui.component.profile.field.ProfileActions
import com.bsuir.game_catalog.ui.component.profile.field.ProfileFields

@Composable
fun DataCard(
    userProfile: UserProfile,
    onUserProfileChange: (UserProfile) -> Unit,
    onSaveProfile: () -> Unit,
    onSignOut: () -> Unit,
    onDeleteAccount: () -> Unit,
) {
    ElevatedCard {
        Column(modifier = Modifier.padding(16.dp)) {
            ProfileFields(
                userProfile = userProfile,
                onUserProfileChange = onUserProfileChange
            )
            Spacer(modifier = Modifier.height(16.dp))
            ProfileActions(
                onSaveProfile = onSaveProfile,
                onSignOut = onSignOut,
                onDeleteAccount = onDeleteAccount
            )
        }
    }
}