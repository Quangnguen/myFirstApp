package com.example.base_app.presentation.ui.screens.loans

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.base_app.presentation.ui.components.*
import com.example.base_app.presentation.ui.theme.AppBackground

@Composable
fun LoanRequestSuccessScreen(
    navController: NavController? = null,
    amount: String = "5,000",
    currency: String = "USDT",
    duration: String = "30 Ngày",
    txHash: String = "0x3a...8f9d"
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            
            // Success Header with Icon and Text
            LoanSuccessHeader()
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Loan Summary Card
            LoanSuccessSummaryCard(
                amount = amount,
                currency = currency,
                duration = duration
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Action Buttons
            LoanSuccessActionButtons(
                onManageLoansClick = {
                    navController?.navigate("loans") {
                        popUpTo("home") { inclusive = false }
                    }
                },
                onGoHomeClick = {
                    navController?.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                onBrowseLoansClick = {
                    navController?.navigate("browse_loans") {
                        popUpTo("home") { inclusive = false }
                    }
                }
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Transaction Hash
            TransactionHashCard(
                txHash = txHash,
                onCopyClick = {
                    // TODO: Copy to clipboard
                }
            )
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
