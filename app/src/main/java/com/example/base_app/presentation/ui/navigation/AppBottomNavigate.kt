
package com.example.base_app.presentation.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.example.base_app.presentation.ui.theme.AppBackground
import com.example.base_app.presentation.ui.theme.CyanButton
import com.example.base_app.presentation.ui.theme.TextGray

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector
)

@Composable
fun AppBottomNavigation(
    currentRoute: String,
    onItemClick: (String) -> Unit,
    navController: NavController?
) {
    val items = listOf(
        BottomNavItem("Trang chủ", "home", Icons.Default.Home),
        BottomNavItem("Khoản vay", "loans", Icons.Default.Refresh),
        BottomNavItem("Ví", "wallet", Icons.Default.AccountBalanceWallet),
        BottomNavItem("Hồ sơ", "profile", Icons.Default.Person)
    )

    NavigationBar(
        containerColor = AppBackground,
        contentColor = TextGray
    ) {
        items.forEach { item ->
            val selected = currentRoute == item.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    // Navigation handled here to avoid duplicate/nested navigation calls
                    if (navController != null) {
                        navController.navigate(item.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    } else {
                        // fallback to callback if navController isn't provided
                        onItemClick(item.route)
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.name,
                        tint = if (selected) CyanButton else TextGray
                    )
                },
                label = {
                    Text(
                        text = item.name,
                        color = if (selected) CyanButton else TextGray
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}