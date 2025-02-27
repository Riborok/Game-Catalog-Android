package com.bsuir.game_catalog.ui.component.profile.field

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.bsuir.game_catalog.R
import com.bsuir.game_catalog.model.UserProfile
import com.bsuir.game_catalog.ui.component.date.DatePickerField

@Composable
fun ProfileFields(
    userProfile: UserProfile,
    onUserProfileChange: (UserProfile) -> Unit,
) {
    val space = 8.dp
    ProfileTextField(
        value = userProfile.firstName,
        onValueChange = { newFirstName ->
            onUserProfileChange(userProfile.copy(firstName = newFirstName))
        },
        label = stringResource(R.string.first_name)
    )
    Spacer(modifier = Modifier.height(space))
    ProfileTextField(
        value = userProfile.lastName,
        onValueChange = { newLastName ->
            onUserProfileChange(userProfile.copy(lastName = newLastName))
        },
        label = stringResource(R.string.last_name)
    )
    Spacer(modifier = Modifier.height(space))
    ProfileTextField(
        value = userProfile.nickname,
        onValueChange = { newNickname ->
            onUserProfileChange(userProfile.copy(nickname = newNickname))
        },
        label = stringResource(R.string.nickname)
    )
    Spacer(modifier = Modifier.height(space))
    DatePickerField(
        date = userProfile.birthDate,
        onDateSelected = { newDate ->
            onUserProfileChange(userProfile.copy(birthDate = newDate))
        },
        label = stringResource(R.string.birth_date)
    )
    Spacer(modifier = Modifier.height(space))
    ProfileTextField(
        value = userProfile.phone,
        onValueChange = { newPhone ->
            onUserProfileChange(userProfile.copy(phone = newPhone))
        },
        label = stringResource(R.string.phone),
        keyboardType = KeyboardType.Phone
    )
    Spacer(modifier = Modifier.height(space))
    ProfileTextField(
        value = userProfile.address,
        onValueChange = { newAddress ->
            onUserProfileChange(userProfile.copy(address = newAddress))
        },
        label = stringResource(R.string.address)
    )
    Spacer(modifier = Modifier.height(space))
    ProfileTextField(
        value = userProfile.city,
        onValueChange = { newCity ->
            onUserProfileChange(userProfile.copy(city = newCity))
        },
        label = stringResource(R.string.city)
    )
    Spacer(modifier = Modifier.height(space))
    ProfileTextField(
        value = userProfile.country,
        onValueChange = { newCountry ->
            onUserProfileChange(userProfile.copy(country = newCountry))
        },
        label = stringResource(R.string.country)
    )
    Spacer(modifier = Modifier.height(space))
    ProfileTextField(
        value = userProfile.description,
        onValueChange = { newDescription ->
            onUserProfileChange(userProfile.copy(description = newDescription))
        },
        label = stringResource(R.string.description)
    )
    Spacer(modifier = Modifier.height(space))
    ProfileTextField(
        value = userProfile.profession,
        onValueChange = { newProfession ->
            onUserProfileChange(userProfile.copy(profession = newProfession))
        },
        label = stringResource(R.string.profession)
    )
}