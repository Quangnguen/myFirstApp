package com.example.base_app.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.base_app.presentation.ui.theme.*

// ==================== Data Classes ====================

data class CryptoAsset(
    val name: String,
    val symbol: String,
    val amount: String,
    val valueInVND: String,
    val iconColor: Color
)

data class WalletTransaction(
    val type: TransactionType,
    val title: String,
    val date: String,
    val amount: String,
    val isPositive: Boolean,
    val status: String
)

enum class TransactionType {
    DEPOSIT, WITHDRAW, INTEREST, LOAN
}

// ==================== Top Bar ====================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletTopBar(
    onBackClick: () -> Unit,
    onNotificationClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = "Ví của tôi",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextWhite
            )
        },
//        navigationIcon = {
//            IconButton(onClick = onBackClick) {
//                Icon(
//                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                    contentDescription = "Back",
//                    tint = TextWhite
//                )
//            }
//        },
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

// ==================== Balance Card ====================

@Composable
fun WalletBalanceCard(
    totalVND: String,
    totalUSDT: String,
    isBalanceVisible: Boolean,
    onToggleVisibility: () -> Unit,
    onDepositClick: () -> Unit,
    onWithdrawClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Total Assets Label with Eye Icon
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Tổng tài sản",
                    fontSize = 14.sp,
                    color = TextGray
                )
                IconButton(
                    onClick = onToggleVisibility,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = if (isBalanceVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle visibility",
                        tint = TextGray,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Main Balance
            Text(
                text = if (isBalanceVisible) totalVND else "********",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = TextWhite
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            // USDT Equivalent
            Text(
                text = if (isBalanceVisible) "≈ $totalUSDT" else "≈ ****",
                fontSize = 14.sp,
                color = TextGray
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            // Action Buttons Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Deposit Button
                Button(
                    onClick = onDepositClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CyanButton
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Nạp tiền",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                
                // Withdraw Button
                OutlinedButton(
                    onClick = onWithdrawClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = CyanButton
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = androidx.compose.ui.graphics.SolidColor(CyanButton)
                    )
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.TrendingUp,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Rút tiền",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

// ==================== Tab Row ====================

@Composable
fun WalletTabRow(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(CardBackground)
            .padding(4.dp)
    ) {
        WalletTab(
            text = "Tài sản",
            isSelected = selectedTab == 0,
            onClick = { onTabSelected(0) },
            modifier = Modifier.weight(1f)
        )
        WalletTab(
            text = "Lịch sử giao dịch",
            isSelected = selectedTab == 1,
            onClick = { onTabSelected(1) },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun WalletTab(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() },
        color = if (isSelected) Color(0xFF374151) else Color.Transparent,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
            color = if (isSelected) TextWhite else TextGray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 16.dp)
        )
    }
}

// ==================== Asset Item ====================

@Composable
fun AssetItem(asset: CryptoAsset) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Crypto Icon
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(asset.iconColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = asset.symbol.first().toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        
        Spacer(modifier = Modifier.width(12.dp))
        
        // Name and Symbol
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = asset.name,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextWhite
            )
            Text(
                text = asset.symbol,
                fontSize = 13.sp,
                color = TextGray
            )
        }
        
        // Amount and Value
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "${asset.amount} ${asset.symbol}",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextWhite
            )
            Text(
                text = asset.valueInVND,
                fontSize = 13.sp,
                color = TextGray
            )
        }
    }
}

// ==================== Transaction Item ====================

@Composable
fun TransactionItem(transaction: WalletTransaction) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Transaction Icon
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(
                    when (transaction.type) {
                        TransactionType.DEPOSIT -> GreenSuccess.copy(alpha = 0.15f)
                        TransactionType.WITHDRAW -> Color(0xFFEF4444).copy(alpha = 0.15f)
                        TransactionType.INTEREST -> CyanButton.copy(alpha = 0.15f)
                        TransactionType.LOAN -> PrimaryBlue.copy(alpha = 0.15f)
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = when (transaction.type) {
                    TransactionType.DEPOSIT -> Icons.Default.Check
                    TransactionType.WITHDRAW -> Icons.Default.ArrowUpward
                    TransactionType.INTEREST -> Icons.Default.Percent
                    TransactionType.LOAN -> Icons.Default.AccountBalance
                },
                contentDescription = null,
                tint = when (transaction.type) {
                    TransactionType.DEPOSIT -> GreenSuccess
                    TransactionType.WITHDRAW -> Color(0xFFEF4444)
                    TransactionType.INTEREST -> CyanButton
                    TransactionType.LOAN -> PrimaryBlue
                },
                modifier = Modifier.size(20.dp)
            )
        }
        
        Spacer(modifier = Modifier.width(12.dp))
        
        // Title and Date
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = transaction.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = TextWhite
            )
            Text(
                text = transaction.date,
                fontSize = 12.sp,
                color = TextGray
            )
        }
        
        // Amount and Status
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = transaction.amount,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (transaction.isPositive) GreenSuccess else TextWhite
            )
            Text(
                text = transaction.status,
                fontSize = 12.sp,
                color = TextGray
            )
        }
    }
}

// ==================== Assets List ====================

@Composable
fun AssetsList(assets: List<CryptoAsset>) {
    Column {
        assets.forEach { asset ->
            AssetItem(asset = asset)
            if (asset != assets.last()) {
                Divider(
                    color = Color(0xFF374151),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

// ==================== Transactions List ====================

@Composable
fun TransactionsList(transactions: List<WalletTransaction>) {
    Column {
        transactions.forEach { transaction ->
            TransactionItem(transaction = transaction)
            if (transaction != transactions.last()) {
                Divider(
                    color = Color(0xFF374151),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}
