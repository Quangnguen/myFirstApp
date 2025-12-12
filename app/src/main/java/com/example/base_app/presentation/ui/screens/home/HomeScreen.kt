package com.example.base_app.presentation.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.base_app.presentation.ui.components.BalanceCard
import com.example.base_app.presentation.ui.components.FeaturedLoanCard
import com.example.base_app.presentation.ui.components.HomeHeader
import com.example.base_app.presentation.ui.components.NFTCollectionCard
import com.example.base_app.presentation.ui.components.QuickActionGrid
import com.example.base_app.presentation.ui.components.UpdateCard
import com.example.base_app.presentation.ui.navigation.AppBottomNavigation
import com.example.base_app.presentation.ui.theme.*

@Composable
fun HomeScreen(navController: NavController? = null) { Scaffold(
        bottomBar = {
            AppBottomNavigation(
                currentRoute = "home",
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
            // 1. Header
            item { HomeHeader() }

            // 2. Balance Card
            item { BalanceCard() }

            // 3. Quick Actions
            item {
                Spacer(modifier = Modifier.height(8.dp))
                QuickActionGrid(
                    onBrowseLoansClick = {
                        navController?.navigate("browse_loans")
                    },
                    onCreateRequestClick = {
                        navController?.navigate("create_loan")
                    },
                    onWalletClick = {
                        navController?.navigate("wallet")
                    }
                )
            }

            // 4. NFT Collection Card
            item {
                Spacer(modifier = Modifier.height(16.dp))
                NFTCollectionCard(
                    nftCount = 12,
                    newCount = 2,
                    onMyNFTClick = {
                        // TODO: Navigate to NFT wallet
                    },
                    onMarketplaceClick = {
                        // TODO: Navigate to NFT marketplace
                    }
                )
            }

            // 5. Featured Loans Title
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    "Khoản Vay Nổi Bật",
                    color = TextWhite,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            // 6. Featured Loans List (Horizontal Scroll)
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    item {
                        FeaturedLoanCard(
                            "Vay kinh doanh - 50M VNDC",
                            "12% APR | 12 Tháng | Điểm A+",
                            gradient = true
                        )
                    }
                    item {
                        FeaturedLoanCard(
                            "Vay sinh viên - 10M VNDC",
                            "10% APR | 24 Tháng",
                            gradient = false
                        )
                    }
                }
            }

            // 7. Updates Title
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    "Cập nhật quan trọng",
                    color = TextWhite,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            // 8. Updates List
            item {
                UpdateCard(
                    icon = Icons.Default.CheckCircle,
                    iconColor = GreenSuccess,
                    title = "Yêu cầu vay đã được duyệt",
                    description = "Khoản vay kinh doanh 50M VNDC của bạn đã được tài trợ thành công."
                )
                UpdateCard(
                    icon = Icons.Default.Warning,
                    iconColor = YellowWarning,
                    title = "Xác minh danh tính của bạn",
                    description = "Tăng hạn mức cho vay của bạn bằng cách hoàn tất xác minh danh tính."
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}