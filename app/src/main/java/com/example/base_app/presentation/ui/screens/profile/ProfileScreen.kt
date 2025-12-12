package com.example.base_app.presentation.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.base_app.presentation.ui.components.*
import com.example.base_app.presentation.ui.navigation.AppBottomNavigation
import com.example.base_app.presentation.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController? = null) {
    var isBalanceVisible by remember { mutableStateOf(true) }
    
    // Sample data
    val transactions = listOf(
        Transaction(
            id = "1",
            title = "Nhận tiền lãi",
            date = "15 Tháng 7, 2024",
            amount = "+ 150,000 VND",
            isPositive = true,
            status = "Hoàn thành",
            statusColor = GreenSuccess
        ),
        Transaction(
            id = "2",
            title = "Rút tiền",
            date = "14 Tháng 7, 2024",
            amount = "- 2,000,000 VND",
            isPositive = false,
            status = "Đang xử lý",
            statusColor = YellowWarning
        )
    )
    
    val menuItems = listOf(
        ProfileMenuItem(Icons.Outlined.Person, "Thông tin cá nhân & KYC"),
        ProfileMenuItem(Icons.Outlined.Shield, "Bảo mật"),
        ProfileMenuItem(Icons.Outlined.CreditCard, "Phương thức thanh toán")
    )

    Scaffold(
        topBar = {
            ProfileTopBar(
                onBackClick = { navController?.popBackStack() },
                onSettingsClick = { }
            )
        },
        bottomBar = {
            AppBottomNavigation(
                currentRoute = "profile",
                onItemClick = {},
                navController = navController
            )
        },
        containerColor = AppBackground
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(AppBackground)
        ) {
            // Profile Header
            item {
                ProfileHeader(
                    name = "Nguyễn Văn A",
                    userId = "user_id: 0x123...abc"
                )
            }
            
            // Balance Card
            item {
                ProfileBalanceCard(
                    totalBalance = "150,000,000 VND",
                    ethEquivalent = "≈ 2.5 ETH",
                    isVisible = isBalanceVisible,
                    onVisibilityToggle = { isBalanceVisible = !isBalanceVisible },
                    onDepositClick = { },
                    onWithdrawClick = { }
                )
            }
            
            // Recent Transactions Section
            item {
                RecentTransactionsHeader(
                    onViewAllClick = { }
                )
            }
            
            // Transaction List
            items(transactions) { transaction ->
                TransactionItem(transaction = transaction)
            }
            
            // Menu Items
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            
            items(menuItems) { menuItem ->
                ProfileMenuItemRow(item = menuItem)
            }
            
            // Logout Button
            item {
                LogoutButton(onClick = { })
            }
            
            // Bottom spacing
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
