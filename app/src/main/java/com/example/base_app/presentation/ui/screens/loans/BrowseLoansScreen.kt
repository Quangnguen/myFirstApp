package com.example.base_app.presentation.ui.screens.loans

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.base_app.presentation.ui.components.*
import com.example.base_app.presentation.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrowseLoansScreen(navController: NavController? = null) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("") }
    
    // Sample loan data
    val loanRequests = listOf(
        LoanRequest(
            id = "1",
            amount = "5.000.000 VNĐ",
            interestRate = "18%/năm",
            duration = "6 tháng",
            creditScore = 750
        ),
        LoanRequest(
            id = "2",
            amount = "10.000.000 VNĐ",
            interestRate = "15%/năm",
            duration = "12 tháng",
            creditScore = 820
        ),
        LoanRequest(
            id = "3",
            amount = "25.000.000 VNĐ",
            interestRate = "12%/năm",
            duration = "24 tháng",
            creditScore = 680
        ),
        LoanRequest(
            id = "4",
            amount = "2.000.000 VNĐ",
            interestRate = "22%/năm",
            duration = "3 tháng",
            creditScore = 550
        )
    )

    Scaffold(
        topBar = {
            BrowseLoansTopBar(
                onBackClick = { navController?.popBackStack() },
                onNotificationClick = { }
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
            // Search Bar
            item {
                Spacer(modifier = Modifier.height(8.dp))
                LoanSearchBar(
                    searchQuery = searchQuery,
                    onSearchChange = { searchQuery = it }
                )
            }
            
            // Filter Chips
            item {
                LoanFilterChips(
                    selectedFilter = selectedFilter,
                    onFilterSelect = { filter ->
                        selectedFilter = if (selectedFilter == filter) "" else filter
                    }
                )
            }
            
            // Divider
            item {
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = CardBackground
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            
            // Loan Request List
            items(loanRequests) { loan ->
                LoanRequestCard(
                    loan = loan,
                    onViewDetailClick = {
                        navController?.navigate("loan_detail/${loan.id}")
                    }
                )
            }
            
            // Bottom spacing
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
