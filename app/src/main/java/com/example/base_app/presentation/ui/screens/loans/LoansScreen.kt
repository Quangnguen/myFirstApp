package com.example.base_app.presentation.ui.screens.loans

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.base_app.presentation.ui.navigation.AppBottomNavigation
import com.example.base_app.presentation.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoansScreen(navController: NavController? = null) {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            AppBottomNavigation(
                currentRoute = "loans",
                onItemClick = {},
                navController = navController
            )
        },
        containerColor = AppBackground,
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(
//                        "Khoản vay của tôi",
//                        color = TextWhite,
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Bold
//                    )
//                },
//                navigationIcon = {
//                    if (navController != null) {
//                        IconButton(onClick = { navController.popBackStack() }) {
//                            Icon(
//                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                                contentDescription = "Back",
//                                tint = TextWhite
//                            )
//                        }
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = AppBackground
//                )
//            )
//        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = CyanButton,
                contentColor = Color.White,
                modifier = Modifier.padding(16.dp)
            ) {
                Text("+", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(AppBackground)
        ) {
            // Tab Bar
            LoanTabBar(selectedTab) { selectedTab = it }

            // Loan List
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppBackground)
            ) {
                if (selectedTab == 0) {
                    // Đã cho vay
                    item {
                        LoanItem(
                            title = "Vay mua xe máy",
                            amount = "10,000,000",
                            amountUnit = "VND",
                            statusLabel = "Đang trả",
                            statusColor = Color(0xFFFFB84D),
                            progress = 0.42f,
                            progressColor = Color(0xFF3B82F6),
                            dueDate = "Xem chi tiết",
                            dueDateLabel = "Kỳ hạn tiếp: 30/08/2024"
                        )
                    }
                    item {
                        LoanItem(
                            title = "Cho may tiêu dùng",
                            amount = "5,000,000",
                            amountUnit = "VND",
                            statusLabel = "Quá hạn",
                            statusColor = Color(0xFFEF4444),
                            progress = 0.75f,
                            progressColor = Color(0xFFEF4444),
                            dueDate = "Xem chi tiết",
                            dueDateLabel = "Kỳ hạn tiếp: 30/09/2024",
                            warningMessage = "Đã quá hạn 5 ngày"
                        )
                    }
                    item {
                        LoanItem(
                            title = "Vay sửa nhà",
                            amount = "25,000,000",
                            amountUnit = "VND",
                            statusLabel = "Hoàn thành",
                            statusColor = GreenSuccess,
                            progress = 1.0f,
                            progressColor = GreenSuccess,
                            dueDate = "Xem chi tiết",
                            dueDateLabel = "Kết thúc: 15/06/2024"
                        )
                    }
                } else {
                    // Đã vay
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Chưa có khoản vay nào",
                                color = TextGray,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }
}