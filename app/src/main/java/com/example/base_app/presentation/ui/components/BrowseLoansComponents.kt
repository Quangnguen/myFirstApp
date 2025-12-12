package com.example.base_app.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.base_app.presentation.ui.theme.*

// ==================== Data Classes ====================

data class LoanRequest(
    val id: String,
    val amount: String,
    val interestRate: String,
    val duration: String,
    val creditScore: Int
)

// ==================== Top Bar ====================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrowseLoansTopBar(
    onBackClick: () -> Unit,
    onNotificationClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Duyệt khoản vay",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextWhite
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = TextWhite
                )
            }
        },
        actions = {
            IconButton(onClick = onNotificationClick) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    tint = TextWhite
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = AppBackground
        )
    )
}

// ==================== Search Bar ====================

@Composable
fun LoanSearchBar(
    searchQuery: String,
    onSearchChange: (String) -> Unit
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        placeholder = {
            Text(
                text = "Tìm kiếm khoản vay...",
                color = TextGray
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = TextGray
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = CardBackground,
            unfocusedContainerColor = CardBackground,
            focusedBorderColor = CyanButton,
            unfocusedBorderColor = Color.Transparent,
            focusedTextColor = TextWhite,
            unfocusedTextColor = TextWhite,
            cursorColor = CyanButton
        ),
        shape = RoundedCornerShape(12.dp),
        singleLine = true
    )
}

// ==================== Filter Chips ====================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoanFilterChips(
    selectedFilter: String,
    onFilterSelect: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterChip(
            selected = selectedFilter == "amount",
            onClick = { onFilterSelect("amount") },
            label = { Text("Số tiền", fontSize = 13.sp) },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            },
            colors = FilterChipDefaults.filterChipColors(
                containerColor = CardBackground,
                selectedContainerColor = CyanButton.copy(alpha = 0.2f),
                labelColor = TextWhite,
                selectedLabelColor = CyanButton
            ),
            border = FilterChipDefaults.filterChipBorder(
                borderColor = Color.Transparent,
                selectedBorderColor = CyanButton,
                enabled = true,
                selected = selectedFilter == "amount"
            )
        )
        
        FilterChip(
            selected = selectedFilter == "rate",
            onClick = { onFilterSelect("rate") },
            label = { Text("Lãi suất", fontSize = 13.sp) },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            },
            colors = FilterChipDefaults.filterChipColors(
                containerColor = CardBackground,
                selectedContainerColor = CyanButton.copy(alpha = 0.2f),
                labelColor = TextWhite,
                selectedLabelColor = CyanButton
            ),
            border = FilterChipDefaults.filterChipBorder(
                borderColor = Color.Transparent,
                selectedBorderColor = CyanButton,
                enabled = true,
                selected = selectedFilter == "rate"
            )
        )
        
        FilterChip(
            selected = selectedFilter == "duration",
            onClick = { onFilterSelect("duration") },
            label = { Text("Thời hạn", fontSize = 13.sp) },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            },
            colors = FilterChipDefaults.filterChipColors(
                containerColor = CardBackground,
                selectedContainerColor = CyanButton.copy(alpha = 0.2f),
                labelColor = TextWhite,
                selectedLabelColor = CyanButton
            ),
            border = FilterChipDefaults.filterChipBorder(
                borderColor = Color.Transparent,
                selectedBorderColor = CyanButton,
                enabled = true,
                selected = selectedFilter == "duration"
            )
        )
        
        FilterChip(
            selected = selectedFilter == "score",
            onClick = { onFilterSelect("score") },
            label = { Text("Điểm", fontSize = 13.sp) },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            },
            colors = FilterChipDefaults.filterChipColors(
                containerColor = CardBackground,
                selectedContainerColor = CyanButton.copy(alpha = 0.2f),
                labelColor = TextWhite,
                selectedLabelColor = CyanButton
            ),
            border = FilterChipDefaults.filterChipBorder(
                borderColor = Color.Transparent,
                selectedBorderColor = CyanButton,
                enabled = true,
                selected = selectedFilter == "score"
            )
        )
    }
}

// ==================== Loan Request Card ====================

@Composable
fun LoanRequestCard(
    loan: LoanRequest,
    onViewDetailClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardBackground
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header row with amount and avatar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Số tiền vay",
                        fontSize = 12.sp,
                        color = TextGray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = loan.amount,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )
                }
                
                // Avatar placeholder
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF374151)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = TextGray,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Loan details row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Interest Rate
                Column {
                    Text(
                        text = "Lãi suất",
                        fontSize = 12.sp,
                        color = TextGray
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = loan.interestRate,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextWhite
                    )
                }
                
                // Duration
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Thời hạn",
                        fontSize = 12.sp,
                        color = TextGray
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = loan.duration,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextWhite
                    )
                }
                
                // Credit Score
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "Điểm tín dụng",
                        fontSize = 12.sp,
                        color = TextGray
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = loan.creditScore.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = getCreditScoreColor(loan.creditScore)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Action Button
            Button(
                onClick = onViewDetailClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CyanButton.copy(alpha = 0.15f)
                ),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                Text(
                    text = "Xem chi tiết và cho vay",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = CyanButton
                )
            }
        }
    }
}

// Helper function to get credit score color
@Composable
private fun getCreditScoreColor(score: Int): Color {
    return when {
        score >= 800 -> GreenSuccess
        score >= 700 -> Color(0xFF4CAF50)
        score >= 600 -> YellowWarning
        else -> Color(0xFFEF5350)
    }
}
