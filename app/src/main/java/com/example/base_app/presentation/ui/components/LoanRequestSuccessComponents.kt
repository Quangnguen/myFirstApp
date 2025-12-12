package com.example.base_app.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
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

// ==================== Success Icon ====================

@Composable
fun LoanSuccessIcon() {
    Box(
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .background(CyanButton),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = "Success",
            tint = Color.White,
            modifier = Modifier.size(48.dp)
        )
    }
}

// ==================== Success Header ====================

@Composable
fun LoanSuccessHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoanSuccessIcon()
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "Yêu cầu đã gửi",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextWhite
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Text(
            text = "Yêu cầu vay của bạn đang được xử lý trên chuỗi khối. Chúng tôi sẽ thông báo khi có người cho vay chấp nhận.",
            fontSize = 14.sp,
            color = TextGray,
            textAlign = TextAlign.Center,
            lineHeight = 22.sp
        )
    }
}

// ==================== Loan Summary Card ====================

@Composable
fun LoanSuccessSummaryCard(
    amount: String,
    currency: String,
    duration: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Số tiền vay",
                    fontSize = 14.sp,
                    color = TextGray
                )
                Text(
                    text = "$amount $currency",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = CyanButton
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Thời hạn",
                    fontSize = 14.sp,
                    color = TextGray
                )
                Text(
                    text = duration,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextWhite
                )
            }
        }
    }
}

// ==================== Action Buttons ====================

@Composable
fun LoanSuccessActionButtons(
    onManageLoansClick: () -> Unit,
    onGoHomeClick: () -> Unit,
    onBrowseLoansClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Primary Button - Manage Loans
        Button(
            onClick = onManageLoansClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = CyanButton
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Quản lý khoản vay của tôi",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // Secondary Button - Go Home
        OutlinedButton(
            onClick = onGoHomeClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = TextWhite
            ),
            border = ButtonDefaults.outlinedButtonBorder.copy(
                brush = androidx.compose.ui.graphics.SolidColor(Color(0xFF374151))
            )
        ) {
            Text(
                text = "Quay về trang chủ",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
        }
        
        Spacer(modifier = Modifier.height(20.dp))
        
        // Browse Other Loans Link
        Row(
            modifier = Modifier.clickable { onBrowseLoansClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Duyệt các khoản vay khác",
                fontSize = 14.sp,
                color = TextGray
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = TextGray,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

// ==================== Transaction Hash ====================

@Composable
fun TransactionHashCard(
    txHash: String,
    onCopyClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFF1E293B)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Loading indicator
                    CircularProgressIndicator(
                        modifier = Modifier.size(16.dp),
                        color = CyanButton,
                        strokeWidth = 2.dp
                    )
                    
                    Text(
                        text = "TxID:",
                        fontSize = 12.sp,
                        color = TextGray
                    )
                    
                    Text(
                        text = txHash,
                        fontSize = 12.sp,
                        color = TextWhite
                    )
                }
                
                IconButton(
                    onClick = onCopyClick,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = "Copy",
                        tint = TextGray,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Đưa lên mạng Blockchain",
            fontSize = 11.sp,
            color = TextGray
        )
    }
}
