package com.example.base_app.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.base_app.presentation.ui.theme.*

// ==================== Data Class ====================

data class LoanConfirmation(
    val loanAmount: String,
    val interestRate: String,
    val duration: String,
    val borrowerName: String,
    val creditScore: Int,
    val startDate: String,
    val platformFee: String,
    val expectedReturn: String,
    val totalReturn: String
)

// ==================== Top Bar ====================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmTransactionTopBar(
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Xác nhận Giao dịch",
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
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = AppBackground
        )
    )
}

// ==================== Amount Header ====================

@Composable
fun ConfirmAmountHeader(
    amount: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "SỐ TIỀN BẠN SẼ CHO VAY",
            fontSize = 12.sp,
            color = TextGray,
            letterSpacing = 1.sp
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Row(
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = amount,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = CyanButton
            )
            Text(
                text = "VND",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = CyanButton,
                modifier = Modifier.padding(top = 8.dp, start = 4.dp)
            )
        }
    }
}

// ==================== Loan Terms Card ====================

@Composable
fun LoanTermsCard(
    interestRate: String,
    duration: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardBackground
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Interest Rate
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(CyanButton.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.TrendingUp,
                        contentDescription = null,
                        tint = CyanButton,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Column {
                    Text(
                        text = "LÃI SUẤT",
                        fontSize = 10.sp,
                        color = TextGray,
                        letterSpacing = 0.5.sp
                    )
                    Text(
                        text = interestRate,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )
                }
            }
            
            // Divider
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(40.dp)
                    .background(Color(0xFF374151))
            )
            
            // Duration
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(CyanButton.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = null,
                        tint = CyanButton,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Column {
                    Text(
                        text = "THỜI HẠN",
                        fontSize = 10.sp,
                        color = TextGray,
                        letterSpacing = 0.5.sp
                    )
                    Text(
                        text = duration,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )
                }
            }
        }
    }
}

// ==================== Borrower Info Card ====================

@Composable
fun BorrowerInfoCard(
    borrowerName: String,
    creditScore: Int,
    startDate: String,
    platformFee: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardBackground
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Borrower Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Avatar
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFFCC80)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = Color(0xFF8D6E63),
                        modifier = Modifier.size(26.dp)
                    )
                }
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Người vay",
                        fontSize = 12.sp,
                        color = TextGray
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = borrowerName,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextWhite
                        )
                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = null,
                            tint = CyanButton,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "Điểm tín dụng",
                        fontSize = 12.sp,
                        color = TextGray
                    )
                    Text(
                        text = creditScore.toString(),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = getCreditColor(creditScore)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            HorizontalDivider(color = Color(0xFF374151))
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Start Date Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Ngày bắt đầu",
                    fontSize = 14.sp,
                    color = TextGray
                )
                Text(
                    text = startDate,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextWhite
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Platform Fee Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Phí nền tảng",
                        fontSize = 14.sp,
                        color = TextGray
                    )
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = TextGray,
                        modifier = Modifier.size(16.dp)
                    )
                }
                Text(
                    text = platformFee,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextWhite
                )
            }
        }
    }
}

@Composable
private fun getCreditColor(score: Int): Color {
    return when {
        score >= 750 -> GreenSuccess
        score >= 650 -> Color(0xFF4CAF50)
        score >= 550 -> YellowWarning
        else -> Color(0xFFEF5350)
    }
}

// ==================== Expected Return Card ====================

@Composable
fun ExpectedReturnCard(
    totalReturn: String,
    profit: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardBackground
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AccountBalanceWallet,
                    contentDescription = null,
                    tint = CyanButton,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "Tổng tiền nhận về dự kiến",
                    fontSize = 14.sp,
                    color = TextGray
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = totalReturn,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
                Text(
                    text = "VND",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextWhite,
                    modifier = Modifier.padding(bottom = 4.dp, start = 4.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = profit,
                fontSize = 14.sp,
                color = GreenSuccess
            )
        }
    }
}

// ==================== Warning Banner ====================

@Composable
fun BlockchainWarningBanner() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = YellowWarning.copy(alpha = 0.1f)
        )
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null,
                tint = YellowWarning,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "Giao dịch này sẽ được ghi lại trên blockchain. Sau khi xác nhận, khoản vay sẽ được giải ngân tự động và không thể hoàn tác.",
                fontSize = 13.sp,
                color = YellowWarning,
                lineHeight = 18.sp
            )
        }
    }
}

// ==================== Bottom Actions ====================

@Composable
fun ConfirmTransactionBottomBar(
    onConfirmClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = CardBackground,
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Confirm Button
            Button(
                onClick = onConfirmClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CyanButton
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Xác nhận & Cho vay",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Cancel Button
            TextButton(
                onClick = onCancelClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Hủy bỏ giao dịch",
                    fontSize = 14.sp,
                    color = TextGray
                )
            }
        }
    }
}
