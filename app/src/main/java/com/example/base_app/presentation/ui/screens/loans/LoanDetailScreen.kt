package com.example.base_app.presentation.ui.screens.loans

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.base_app.presentation.ui.components.*
import com.example.base_app.presentation.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoanDetailScreen(
    navController: NavController? = null,
    loanId: String? = null
) {
    // Sample loan detail data
    val loanDetail = LoanDetail(
        id = loanId ?: "1",
        amount = "50.000.000đ",
        interestRate = "12%",
        duration = "6 tháng",
        riskLevel = "Thấp",
        borrowerName = "Nguyễn Văn A",
        borrowerType = "Tin cậy cao",
        creditScore = 720,
        maxCreditScore = 850,
        monthlyPayment = "VeriTed",
        totalInterest = "0%",
        totalLoanCount = "Lần 2",
        loanPurpose = "Mục đích vay",
        businessType = "Kinh doanh online",
        createdDate = "20 Th10, 2023",
        smartContractAddress = "0x6F...4a21",
        timeline = listOf(
            TimelineItem(
                title = "Yêu cầu vay được tạo",
                date = "10:30 AM - 20/10/2023",
                isCompleted = true
            ),
            TimelineItem(
                title = "Đã xác minh danh tính (KYC)",
                date = "10:35 AM - 20/10/2023",
                isCompleted = true
            ),
            TimelineItem(
                title = "Giải ngân",
                date = "Đang chờ nhà đầu tư",
                isCompleted = false
            )
        )
    )

    Scaffold(
        topBar = {
            LoanDetailTopBar(
                onBackClick = { navController?.popBackStack() },
                onShareClick = { }
            )
        },
        bottomBar = {
            LoanDetailBottomBar(
                onMessageClick = { },
                onInvestClick = {
                    navController?.navigate("confirm_transaction/${loanId ?: "1"}")
                }
            )
        },
        containerColor = AppBackground
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(AppBackground),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Loan Amount Card
            item {
                Spacer(modifier = Modifier.height(8.dp))
                LoanAmountCard(
                    amount = loanDetail.amount,
                    interestRate = loanDetail.interestRate,
                    duration = loanDetail.duration,
                    riskLevel = loanDetail.riskLevel
                )
            }
            
            // Parties Section
            item {
                PartiesSection(
                    borrowerName = loanDetail.borrowerName,
                    borrowerType = loanDetail.borrowerType
                )
            }
            
            // Credit Score Section
            item {
                CreditScoreSection(
                    score = loanDetail.creditScore,
                    maxScore = loanDetail.maxCreditScore,
                    monthlyPayment = loanDetail.monthlyPayment,
                    totalInterest = loanDetail.totalInterest,
                    loanCount = loanDetail.totalLoanCount
                )
            }
            
            // Loan Details Section
            item {
                LoanDetailsSection(
                    purpose = loanDetail.loanPurpose,
                    businessType = loanDetail.businessType,
                    createdDate = loanDetail.createdDate,
                    contractAddress = loanDetail.smartContractAddress
                )
            }
            
            // Timeline Section
            item {
                TimelineSection(items = loanDetail.timeline)
            }
            
            // Disclaimer
            item {
                Text(
                    text = "Đầu tư P2P luôn có rủi ro vốn. Vui lòng đọc Điều khoản & Điều kiện trước khi thực hiện giao dịch.",
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    color = TextGray,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            
            // Bottom spacing
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
