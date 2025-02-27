package com.bsuir.game_catalog.ui.component.nav

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RowScope.NavigationItem(
    icon: ImageVector,
    contentDescription: String,
    route: String,
    currentRoute: String?,
    navController: NavController
) {
    val selected = currentRoute == route
    val iconSize = if (selected) 36.dp else 26.dp
    val iconColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface

    NavigationBarItem(
        icon = {
            Icon(
                icon,
                contentDescription = contentDescription,
                modifier = Modifier.size(iconSize),
                tint = iconColor
            )
        },
        selected = selected,
        alwaysShowLabel = false,
        onClick = {
            if (!selected) {
                navController.navigate(route) {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    )
}
