package com.example.base_app.presentation.ui.screens.loans

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.base_app.presentation.ui.components.*
import com.example.base_app.presentation.ui.theme.AppBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmLoanRequestScreen(
    navController: NavController? = null,
    // These would normally come from navigation arguments or ViewModel
    amount: String = "5,000",
    currency: String = "USDT",
    network: String = "ETHEREUM",
    interestRate: String = "5.5% / tháng",
    duration: String = "30 Ngày",
    purpose: String = "Kinh doanh",
    totalRepayment: String = "5,022.5"
) {
    // Sample NFT collateral data
    val collateralNFT = remember {
        CollateralNFT(
            name = "Cyber Ape",
            tokenId = "8817",
            collection = "Cyber Punks Club",
            estimatedValue = "4.5 ETH",
            tokenStandard = "ERC-721"
        )
    }
    
    Scaffold(
        topBar = {
            ConfirmLoanRequestTopBar(
                onBackClick = { navController?.popBackStack() }
            )
        },
        bottomBar = {
            LoanRequestBottomBar(
                onEditClick = { navController?.popBackStack() },
                onSubmitClick = {
                    // Navigate to success screen
                    navController?.navigate("loan_request_success") {
                        popUpTo("create_loan") { inclusive = true }
                    }
                }
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
            // Amount Header
            LoanRequestAmountHeader(
                amount = amount,
                currency = currency,
                network = network
            )
            
            // Loan Details Card
            LoanRequestDetailsCard(
                interestRate = interestRate,
                duration = duration,
                purpose = purpose,
                totalRepayment = totalRepayment,
                currency = currency
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            // Collateral NFT Card
            CollateralNFTCard(nft = collateralNFT)
            
            Spacer(modifier = Modifier.height(20.dp))
            
            // Blockchain Warning
            LoanRequestBlockchainWarning()
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
