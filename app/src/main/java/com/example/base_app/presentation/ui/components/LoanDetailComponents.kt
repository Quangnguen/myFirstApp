package com.example.base_app.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.base_app.presentation.ui.theme.*

// ==================== Data Classes ====================

data class LoanDetail(
    val id: String,
    val amount: String,
    val interestRate: String,
    val duration: String,
    val riskLevel: String,
    val borrowerName: String,
    val borrowerType: String,
    val creditScore: Int,
    val maxCreditScore: Int = 850,
    val monthlyPayment: String,
    val totalInterest: String,
    val totalLoanCount: String,
    val loanPurpose: String,
    val businessType: String,
    val createdDate: String,
    val smartContractAddress: String,
    val timeline: List<TimelineItem>
)

data class TimelineItem(
    val title: String,
    val date: String,
    val isCompleted: Boolean
)

// ==================== Top Bar ====================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoanDetailTopBar(
    onBackClick: () -> Unit,
    onShareClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Chi tiết Khoản vay",
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
            IconButton(onClick = onShareClick) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    tint = TextWhite
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = AppBackground
        )
    )
}

// ==================== Status Badge ====================

@Composable
fun LoanStatusBadge(
    status: String,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when (status.lowercase()) {
        "đang chờ duyệt" -> YellowWarning.copy(alpha = 0.15f)
        "đã duyệt" -> GreenSuccess.copy(alpha = 0.15f)
        "từ chối" -> Color(0xFFEF5350).copy(alpha = 0.15f)
        else -> CyanButton.copy(alpha = 0.15f)
    }
    val textColor = when (status.lowercase()) {
        "đang chờ duyệt" -> YellowWarning
        "đã duyệt" -> GreenSuccess
        "từ chối" -> Color(0xFFEF5350)
        else -> CyanButton
    }
    
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Schedule,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(14.dp)
            )
            Text(
                text = status.uppercase(),
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = textColor
            )
        }
    }
}

// ==================== Loan Amount Card ====================

@Composable
fun LoanAmountCard(
    amount: String,
    interestRate: String,
    duration: String,
    riskLevel: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFFFE082),
                            Color(0xFF81D4FA)
                        )
                    )
                )
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoanStatusBadge(status = "Đang chờ duyệt")
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Text(
                    text = "Số tiền vay",
                    fontSize = 14.sp,
                    color = Color.Black.copy(alpha = 0.7f)
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = amount,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                
                Spacer(modifier = Modifier.height(20.dp))
                
                // Details row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    LoanInfoItem(
                        label = "Lãi suất\n(APR)",
                        value = interestRate,
                        valueColor = Color.Black
                    )
                    LoanInfoItem(
                        label = "Kỳ hạn",
                        value = duration,
                        valueColor = Color.Black
                    )
                    LoanInfoItem(
                        label = "Rủi ro",
                        value = riskLevel,
                        valueColor = getRiskColor(riskLevel),
                        showBorder = true
                    )
                }
            }
        }
    }
}

@Composable
private fun LoanInfoItem(
    label: String,
    value: String,
    valueColor: Color = Color.Black,
    showBorder: Boolean = false
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Black.copy(alpha = 0.6f),
            textAlign = TextAlign.Center,
            lineHeight = 16.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        if (showBorder) {
            Surface(
                shape = RoundedCornerShape(6.dp),
                color = Color.White.copy(alpha = 0.8f),
                modifier = Modifier.border(
                    width = 1.dp,
                    color = valueColor,
                    shape = RoundedCornerShape(6.dp)
                )
            ) {
                Text(
                    text = value,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = valueColor,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                )
            }
        } else {
            Text(
                text = value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = valueColor
            )
        }
    }
}

private fun getRiskColor(risk: String): Color {
    return when (risk.lowercase()) {
        "thấp" -> GreenSuccess
        "trung bình" -> YellowWarning
        "cao" -> Color(0xFFEF5350)
        else -> TextGray
    }
}

// ==================== Parties Section ====================

@Composable
fun PartiesSection(
    borrowerName: String,
    borrowerType: String
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
            Text(
                text = "Thông tin các bên",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextWhite
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Borrower
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFFCC80)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = Color(0xFF8D6E63),
                        modifier = Modifier.size(28.dp)
                    )
                }
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "NGƯỜI ĐI VAY",
                        fontSize = 10.sp,
                        color = TextGray,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = borrowerName,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextWhite
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = null,
                            tint = CyanButton,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = borrowerType,
                            fontSize = 12.sp,
                            color = CyanButton
                        )
                    }
                }
                
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.PersonAdd,
                        contentDescription = "Add",
                        tint = TextGray
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Investor (waiting)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .border(1.dp, TextGray.copy(alpha = 0.3f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.HourglassEmpty,
                        contentDescription = null,
                        tint = TextGray,
                        modifier = Modifier.size(24.dp)
                    )
                }
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Column {
                    Text(
                        text = "NHÀ ĐẦU TƯ",
                        fontSize = 10.sp,
                        color = TextGray,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Đang chờ khớp lệnh",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextGray
                    )
                    Text(
                        text = "Có thể là bạn",
                        fontSize = 12.sp,
                        color = TextGray.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}

// ==================== Credit Score Section ====================

@Composable
fun CreditScoreSection(
    score: Int,
    maxScore: Int,
    monthlyPayment: String,
    totalInterest: String,
    loanCount: String
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
            Text(
                text = "Hồ sơ tín dụng",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextWhite
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Credit Score Display
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "ĐIỂM TÍN DỤNG",
                        fontSize = 10.sp,
                        color = TextGray,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = score.toString(),
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = getCreditScoreColor(score)
                        )
                        Text(
                            text = " / $maxScore",
                            fontSize = 16.sp,
                            color = TextGray,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }
                }
                
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = GreenSuccess.copy(alpha = 0.15f)
                ) {
                    Text(
                        text = "RẤT TỐT",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = GreenSuccess,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Credit Score Progress Bar
            LinearProgressIndicator(
                progress = { score.toFloat() / maxScore },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = getCreditScoreColor(score),
                trackColor = Color(0xFF374151)
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            // Details Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CreditDetailItem(
                    label = "Trả/tháng",
                    value = monthlyPayment
                )
                CreditDetailItem(
                    label = "Nợ xấu",
                    value = totalInterest
                )
                CreditDetailItem(
                    label = "Khoản vay",
                    value = loanCount
                )
            }
        }
    }
}

@Composable
private fun CreditDetailItem(
    label: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = TextGray
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextWhite
        )
    }
}

@Composable
private fun getCreditScoreColor(score: Int): Color {
    return when {
        score >= 750 -> GreenSuccess
        score >= 650 -> Color(0xFF4CAF50)
        score >= 550 -> YellowWarning
        else -> Color(0xFFEF5350)
    }
}

// ==================== Loan Details Section ====================

@Composable
fun LoanDetailsSection(
    purpose: String,
    businessType: String,
    createdDate: String,
    contractAddress: String
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
            Text(
                text = "Thông tin chi tiết",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextWhite
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            DetailRow(
                icon = Icons.Default.AccountBalance,
                label = "Mục đích vay\nPhát triển kinh doanh",
                value = businessType
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            DetailRow(
                icon = Icons.Default.CalendarMonth,
                label = "Ngày tạo\nThời gian khởi tạo",
                value = createdDate
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            DetailRow(
                icon = Icons.Default.Code,
                label = "Smart Contract\nBSC Network",
                value = contractAddress,
                isAddress = true
            )
        }
    }
}

@Composable
private fun DetailRow(
    icon: ImageVector,
    label: String,
    value: String,
    isAddress: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF374151)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = CyanButton,
                modifier = Modifier.size(20.dp)
            )
        }
        
        Spacer(modifier = Modifier.width(12.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            val lines = label.split("\n")
            Text(
                text = lines.getOrNull(0) ?: "",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = TextWhite
            )
            if (lines.size > 1) {
                Text(
                    text = lines.getOrNull(1) ?: "",
                    fontSize = 12.sp,
                    color = TextGray
                )
            }
        }
        
        if (isAddress) {
            Text(
                text = value,
                fontSize = 13.sp,
                color = CyanButton
            )
        } else {
            Text(
                text = value,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = TextWhite
            )
        }
    }
}

// ==================== Timeline Section ====================

@Composable
fun TimelineSection(
    items: List<TimelineItem>
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
            Text(
                text = "Tiến trình",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextWhite
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            items.forEachIndexed { index, item ->
                TimelineItemRow(
                    item = item,
                    isLast = index == items.lastIndex
                )
                if (index < items.lastIndex) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
private fun TimelineItemRow(
    item: TimelineItem,
    isLast: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Timeline indicator
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(
                        if (item.isCompleted) CyanButton else Color(0xFF374151)
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (item.isCompleted) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(40.dp)
                        .background(
                            if (item.isCompleted) CyanButton.copy(alpha = 0.5f)
                            else Color(0xFF374151)
                        )
                )
            }
        }
        
        Spacer(modifier = Modifier.width(12.dp))
        
        // Content
        Column {
            Text(
                text = item.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = if (item.isCompleted) TextWhite else TextGray
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = item.date,
                fontSize = 12.sp,
                color = TextGray
            )
        }
    }
}

// ==================== Bottom Action Bar ====================

@Composable
fun LoanDetailBottomBar(
    onMessageClick: () -> Unit,
    onInvestClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = CardBackground,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Message Button
            IconButton(
                onClick = onMessageClick,
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF374151))
            ) {
                Icon(
                    imageVector = Icons.Default.Chat,
                    contentDescription = "Message",
                    tint = CyanButton
                )
            }
            
            // Invest Button
            Button(
                onClick = onInvestClick,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CyanButton
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Đầu tư ngay",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
