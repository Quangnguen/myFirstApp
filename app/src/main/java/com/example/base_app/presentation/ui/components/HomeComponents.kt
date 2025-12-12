package com.example.base_app.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.base_app.presentation.ui.theme.*

@Composable
fun HomeHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Avatar Placeholder
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFFCC80)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, contentDescription = null, tint = Color.Black)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Chào mừng, Anh!",
                color = TextWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        
        // Notification Icon with Badge
        Box {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications",
                tint = TextWhite,
                modifier = Modifier.size(28.dp)
            )
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(PrimaryBlue)
                    .align(Alignment.TopEnd)
            )
        }
    }
}

@Composable
fun BalanceCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text("Tổng số dư", color = TextGray, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "15,000,000 VNDC",
                color = TextWhite,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Khoản vay hoạt động: 2 | Khoản đầu tư: 1",
                color = TextGray,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = CyanButton),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Nạp tiền", color = TextWhite)
                }
                Button(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF374151)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Rút tiền", color = TextWhite)
                }
            }
        }
    }
}

@Composable
fun QuickActionGrid(
    onBrowseLoansClick: () -> Unit = {},
    onCreateRequestClick: () -> Unit = {},
    onWalletClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        QuickActionItem(
            icon = Icons.Default.Search,
            label = "Duyệt\nKhoản Vay",
            onClick = onBrowseLoansClick
        )
        QuickActionItem(
            icon = Icons.Default.Add,
            label = "Tạo Yêu\nCầu",
            onClick = onCreateRequestClick
        )
        QuickActionItem(
            icon = Icons.Default.AccountBalanceWallet,
            label = "Ví Của Tôi",
            onClick = onWalletClick
        )
    }
}

@Composable
fun QuickActionItem(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF374151)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = CyanButton)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = label,
                color = TextWhite,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
fun FeaturedLoanCard(title: String, subtitle: String, gradient: Boolean = false) {
    val backgroundModifier = if (gradient) {
        Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(GradientStart, GradientEnd)
            )
        )
    } else {
        Modifier.background(CardBackground)
    }

    Card(
        modifier = Modifier
            .width(280.dp)
            .height(160.dp)
            .padding(end = 16.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize().then(backgroundModifier)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(title, color = TextWhite, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(subtitle, color = if(gradient) Color.White.copy(alpha = 0.8f) else TextGray, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if(gradient) Color.Black.copy(alpha = 0.3f) else Color(0xFF374151)
                    ),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                    modifier = Modifier.height(32.dp)
                ) {
                    Text("Xem Chi Tiết", fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
fun UpdateCard(icon: ImageVector, iconColor: Color, title: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, color = TextWhite, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(description, color = TextGray, fontSize = 12.sp)
            }
        }
    }
}

// ==================== NFT Collection Card ====================

@Composable
fun NFTCollectionCard(
    nftCount: Int = 12,
    newCount: Int = 2,
    onMyNFTClick: () -> Unit = {},
    onMarketplaceClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Left side - Title and Count
                Column {
                    Text(
                        text = "NFT & Bộ sưu tập",
                        color = TextWhite,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Tài sản sở hữu",
                        color = TextGray,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "$nftCount NFTs",
                            color = TextWhite,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        // New badge
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = CyanButton.copy(alpha = 0.2f)
                        ) {
                            Text(
                                text = "+$newCount mới",
                                color = CyanButton,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
                
                // Right side - NFT Thumbnails (overlapping circles)
                Box(
                    modifier = Modifier.width(80.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    // Third circle (back)
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .offset(x = 0.dp)
                            .clip(CircleShape)
                            .background(CyanButton)
                    )
                    // Second circle (middle)
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .offset(x = (-20).dp)
                            .clip(CircleShape)
                            .background(PrimaryBlue)
                    )
                    // First circle (front)
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .offset(x = (-40).dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFF9800))
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Action Buttons Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // My NFT Button
                Button(
                    onClick = onMyNFTClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF374151)
                    ),
                    shape = RoundedCornerShape(20.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountBalanceWallet,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = TextWhite
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Ví NFT của tôi",
                        fontSize = 12.sp,
                        color = TextWhite
                    )
                }
                
                // Marketplace Button
                Button(
                    onClick = onMarketplaceClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CyanButton
                    ),
                    shape = RoundedCornerShape(20.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Store,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Thị trường N...",
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}