package com.bsuir.game_catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bsuir.game_catalog.viewmodel.AuthViewModel

@Composable
fun MainContent(authViewModel: AuthViewModel, navController: NavController) {
    val user by authViewModel.user.observeAsState()

    LaunchedEffect(user) {
        if (user == null) {
            navController.navigate(Routes.AUTH) {
                popUpTo(Routes.MAIN) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Добро пожаловать, ${user?.email}")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { authViewModel.signOut() }) {
            Text("Выйти")
        }
    }
}