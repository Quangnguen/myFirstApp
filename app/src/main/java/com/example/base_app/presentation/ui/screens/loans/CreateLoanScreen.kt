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
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateLoanScreen(
    navController: NavController? = null
) {
    // State variables
    var loanAmount by remember { mutableFloatStateOf(10_000_000f) }
    var interestRate by remember { mutableStateOf("12.5") }
    var selectedDuration by remember { mutableIntStateOf(6) }
    
    var selectedPurpose by remember { mutableStateOf("Kinh doanh") }
    var purposeExpanded by remember { mutableStateOf(false) }
    var purposeDescription by remember { mutableStateOf("") }
    
    var collateralType by remember { mutableStateOf("NFT") }
    var contractAddress by remember { mutableStateOf("") }
    var tokenId by remember { mutableStateOf("") }
    var selectedNetwork by remember { mutableStateOf("Ethereum") }
    var networkExpanded by remember { mutableStateOf(false) }
    
    // Calculate loan summary
    val rate = (interestRate.toFloatOrNull() ?: 12.5f) / 100 / 12
    val months = selectedDuration
    val principal = loanAmount.toDouble()
    
    val monthlyPayment = if (rate > 0) {
        principal * rate * Math.pow(1 + rate.toDouble(), months.toDouble()) / 
        (Math.pow(1 + rate.toDouble(), months.toDouble()) - 1)
    } else {
        principal / months
    }
    
    val totalRepayment = monthlyPayment * months
    val totalInterest = totalRepayment - principal
    
    val formatter = NumberFormat.getNumberInstance(Locale("vi", "VN"))
    
    Scaffold(
        topBar = {
            CreateLoanTopBar(
                onBackClick = { navController?.popBackStack() }
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
            
            // Loan Amount Section
            LoanAmountSection(
                amount = loanAmount,
                onAmountChange = { loanAmount = it }
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Interest Rate & Duration
            InterestDurationRow(
                interestRate = interestRate,
                onInterestChange = { interestRate = it },
                duration = "$selectedDuration tháng"
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Duration Chips
            DurationChipsRow(
                selectedDuration = selectedDuration,
                onDurationSelect = { selectedDuration = it }
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            // Loan Purpose Section
            LoanPurposeSection(
                selectedPurpose = selectedPurpose,
                onPurposeChange = { selectedPurpose = it },
                expanded = purposeExpanded,
                onExpandedChange = { purposeExpanded = it },
                description = purposeDescription,
                onDescriptionChange = { purposeDescription = it }
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            // Collateral Section
            CollateralSection(
                selectedType = collateralType,
                onTypeSelect = { collateralType = it },
                contractAddress = contractAddress,
                onContractAddressChange = { contractAddress = it },
                tokenId = tokenId,
                onTokenIdChange = { tokenId = it },
                selectedNetwork = selectedNetwork,
                onNetworkChange = { selectedNetwork = it },
                networkExpanded = networkExpanded,
                onNetworkExpandedChange = { networkExpanded = it }
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            // Loan Summary
            LoanSummarySection(
                monthlyPayment = "${formatter.format(monthlyPayment.toLong())} VNĐ",
                totalInterest = "${formatter.format(totalInterest.toLong())} VNĐ",
                totalRepayment = "${formatter.format(totalRepayment.toLong())} VNĐ"
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Submit Button
            SubmitLoanButton(
                onClick = {
                    navController?.navigate("confirm_loan_request")
                }
            )
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
