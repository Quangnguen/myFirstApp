package com.example.base_app.presentation.ui.screens.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.base_app.presentation.ui.components.*
import com.example.base_app.presentation.ui.navigation.AppBottomNavigation
import com.example.base_app.presentation.ui.theme.AppBackground
import com.example.base_app.presentation.ui.theme.GreenSuccess

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletScreen(
    navController: NavController? = null
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    var isBalanceVisible by remember { mutableStateOf(true) }
    
    // Sample data for assets
    val assets = remember {
        listOf(
            CryptoAsset(
                name = "Tether",
                symbol = "USDT",
                amount = "500.00",
                valueInVND = "12,500,000 VNĐ",
                iconColor = Color(0xFF26A17B)
            ),
            CryptoAsset(
                name = "Bitcoin",
                symbol = "BTC",
                amount = "0.0025",
                valueInVND = "1,500,000 VNĐ",
                iconColor = Color(0xFFF7931A)
            ),
            CryptoAsset(
                name = "Ethereum",
                symbol = "ETH",
                amount = "0.028",
                valueInVND = "1,000,000 VNĐ",
                iconColor = Color(0xFF627EEA)
            )
        )
    }
    
    // Sample data for transactions
    val transactions = remember {
        listOf(
            WalletTransaction(
                type = TransactionType.DEPOSIT,
                title = "Nạp tiền thành công",
                date = "10/05/2024 09:30",
                amount = "+ 500.00 USDT",
                isPositive = true,
                status = "Hoàn thành"
            ),
            WalletTransaction(
                type = TransactionType.WITHDRAW,
                title = "Rút tiền",
                date = "08/05/2024 15:45",
                amount = "- 100.00 USDT",
                isPositive = false,
                status = "Hoàn thành"
            ),
            WalletTransaction(
                type = TransactionType.INTEREST,
                title = "Nhận lãi vay",
                date = "05/05/2024 11:20",
                amount = "+ 5.50 USDT",
                isPositive = true,
                status = "Hoàn thành"
            ),
            WalletTransaction(
                type = TransactionType.LOAN,
                title = "Cho vay",
                date = "01/05/2024 18:00",
                amount = "- 200.00 USDT",
                isPositive = false,
                status = "Hoàn thành"
            )
        )
    }
    
    Scaffold(
        topBar = {
            WalletTopBar(
                onBackClick = { navController?.popBackStack() }
            )
        },
        bottomBar = {
            AppBottomNavigation(
                currentRoute = "wallet",
                onItemClick = {},
                navController = navController
            )
        },
        containerColor = AppBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .background(AppBackground)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            
            // Balance Card
            WalletBalanceCard(
                totalVND = "15,000,000 VNĐ",
                totalUSDT = "600.00 USDT",
                isBalanceVisible = isBalanceVisible,
                onToggleVisibility = { isBalanceVisible = !isBalanceVisible },
                onDepositClick = {
                    // TODO: Navigate to deposit screen
                },
                onWithdrawClick = {
                    // TODO: Navigate to withdraw screen
                }
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            // Tab Row
            WalletTabRow(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Content based on selected tab
            when (selectedTab) {
                0 -> AssetsList(assets = assets)
                1 -> TransactionsList(transactions = transactions)
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
