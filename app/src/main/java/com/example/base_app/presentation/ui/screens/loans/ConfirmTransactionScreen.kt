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
fun ConfirmTransactionScreen(
    navController: NavController? = null,
    loanId: String? = null
) {
    // Sample confirmation data based on loan
    val confirmation = LoanConfirmation(
        loanAmount = "10.000.000",
        interestRate = "12% / năm",
        duration = "6 tháng",
        borrowerName = "Nguyễn Văn A",
        creditScore = 720,
        startDate = "24/10/2023",
        platformFee = "50.000 VND",
        expectedReturn = "10.600.000",
        totalReturn = "+600.000 VND lợi nhuận"
    )

    Scaffold(
        topBar = {
            ConfirmTransactionTopBar(
                onBackClick = { navController?.popBackStack() }
            )
        },
        bottomBar = {
            ConfirmTransactionBottomBar(
                onConfirmClick = {
                    // TODO: Process transaction and navigate to success screen
                    navController?.popBackStack()
                },
                onCancelClick = {
                    navController?.popBackStack()
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
            // Amount Header
            item {
                ConfirmAmountHeader(amount = confirmation.loanAmount)
            }
            
            // Loan Terms Card
            item {
                LoanTermsCard(
                    interestRate = confirmation.interestRate,
                    duration = confirmation.duration
                )
            }
            
            // Borrower Info Card
            item {
                BorrowerInfoCard(
                    borrowerName = confirmation.borrowerName,
                    creditScore = confirmation.creditScore,
                    startDate = confirmation.startDate,
                    platformFee = confirmation.platformFee
                )
            }
            
            // Expected Return Card
            item {
                ExpectedReturnCard(
                    totalReturn = confirmation.expectedReturn,
                    profit = confirmation.totalReturn
                )
            }
            
            // Warning Banner
            item {
                BlockchainWarningBanner()
            }
            
            // Bottom spacing
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
