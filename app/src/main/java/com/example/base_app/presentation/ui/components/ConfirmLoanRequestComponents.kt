package com.example.base_app.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.base_app.presentation.ui.theme.*

// ==================== Data Classes ====================

data class LoanRequestConfirmation(
    val amount: String,
    val currency: String,
    val network: String,
    val interestRate: String,
    val duration: String,
    val purpose: String,
    val totalRepayment: String,
    val collateral: CollateralNFT
)

data class CollateralNFT(
    val name: String,
    val tokenId: String,
    val collection: String,
    val estimatedValue: String,
    val tokenStandard: String = "ERC-721"
)

// ==================== Top Bar ====================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmLoanRequestTopBar(
    onBackClick: () -> Unit,
    onMoreClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = "Xác nhận Yêu Cầu Vay",
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
            IconButton(onClick = onMoreClick) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More",
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
fun LoanRequestAmountHeader(
    amount: String,
    currency: String,
    network: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Số tiền muốn vay",
            fontSize = 14.sp,
            color = TextGray
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "$amount $currency",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = CyanButton
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = CyanButton.copy(alpha = 0.15f)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Verified,
                    contentDescription = null,
                    tint = CyanButton,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "MẠNG $network",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = CyanButton
                )
            }
        }
    }
}

// ==================== Loan Details Card ====================

@Composable
fun LoanRequestDetailsCard(
    interestRate: String,
    duration: String,
    purpose: String,
    totalRepayment: String,
    currency: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Chi tiết Khoản vay",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextWhite
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            LoanRequestDetailRow(
                label = "Lãi suất đề xuất",
                value = interestRate,
                valueColor = CyanButton
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            LoanRequestDetailRow(
                label = "Thời hạn vay",
                value = duration
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            LoanRequestDetailRow(
                label = "Mục đích vay",
                value = purpose
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Divider(color = Color(0xFF374151))
            
            Spacer(modifier = Modifier.height(12.dp))
            
            LoanRequestDetailRow(
                label = "Tổng trả dự kiến",
                value = "$totalRepayment $currency",
                valueColor = CyanButton,
                isBold = true
            )
        }
    }
}

@Composable
private fun LoanRequestDetailRow(
    label: String,
    value: String,
    valueColor: Color = TextWhite,
    isBold: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = TextGray
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Medium,
            color = valueColor
        )
    }
}

// ==================== Collateral NFT Card ====================

@Composable
fun CollateralNFTCard(
    nft: CollateralNFT
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Tài sản Thế chấp",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextWhite
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = CardBackground)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // NFT Image Placeholder
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF1E3A5F),
                                    Color(0xFF0D2137)
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    // Placeholder for NFT image
                    Icon(
                        imageVector = Icons.Default.Image,
                        contentDescription = null,
                        tint = CyanButton,
                        modifier = Modifier.size(40.dp)
                    )
                }
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "${nft.name} #${nft.tokenId}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextWhite
                        )
                        
                        // Token Standard Badge
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = CyanButton.copy(alpha = 0.2f)
                        ) {
                            Text(
                                text = nft.tokenStandard,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Medium,
                                color = CyanButton,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    Text(
                        text = "Bộ sưu tập: ${nft.collection}",
                        fontSize = 12.sp,
                        color = TextGray
                    )
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Định giá:",
                            fontSize = 12.sp,
                            color = TextGray
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "~ ${nft.estimatedValue}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextWhite
                        )
                    }
                }
            }
        }
    }
}

// ==================== Blockchain Warning ====================

@Composable
fun LoanRequestBlockchainWarning(
    gasFee: String = "0.002 ETH"
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFF1E293B),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = CyanButton,
                modifier = Modifier.size(18.dp)
            )
            
            Spacer(modifier = Modifier.width(10.dp))
            
            Column {
                Text(
                    text = "Yêu cầu vay của bạn sẽ được đưa lên mạng blockchain.",
                    fontSize = 12.sp,
                    color = TextGray,
                    lineHeight = 18.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Text(
                        text = "Phí gas ước tính là ",
                        fontSize = 12.sp,
                        color = TextGray
                    )
                    Text(
                        text = gasFee,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = CyanButton
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Hành động này không thể hoàn tác sau khi được xác nhận trên chuỗi.",
                    fontSize = 12.sp,
                    color = TextGray,
                    lineHeight = 18.sp
                )
            }
        }
    }
}

// ==================== Bottom Action Bar ====================

@Composable
fun LoanRequestBottomBar(
    onEditClick: () -> Unit,
    onSubmitClick: () -> Unit
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
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Edit Button
            OutlinedButton(
                onClick = onEditClick,
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = CyanButton
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = androidx.compose.ui.graphics.SolidColor(CyanButton)
                )
            ) {
                Text(
                    text = "Chỉnh sửa",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            
            // Submit Button
            Button(
                onClick = onSubmitClick,
                modifier = Modifier
                    .weight(1.5f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CyanButton
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Gửi Yêu Cầu Vay",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}
