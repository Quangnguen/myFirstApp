package com.example.base_app.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.base_app.presentation.ui.theme.*
import java.text.NumberFormat
import java.util.Locale

// ==================== Top Bar ====================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateLoanTopBar(
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Tạo Yêu Cầu Vay",
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

// ==================== Loan Amount Section ====================

@Composable
fun LoanAmountSection(
    amount: Float,
    onAmountChange: (Float) -> Unit,
    minAmount: Float = 1_000_000f,
    maxAmount: Float = 50_000_000f
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
                text = "Số tiền vay (VNĐ)",
                fontSize = 12.sp,
                color = TextGray
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = formatCurrency(amount.toLong()),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = TextWhite
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Slider(
                value = amount,
                onValueChange = onAmountChange,
                valueRange = minAmount..maxAmount,
                steps = 48,
                colors = SliderDefaults.colors(
                    thumbColor = CyanButton,
                    activeTrackColor = CyanButton,
                    inactiveTrackColor = Color(0xFF374151)
                )
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "1tr",
                    fontSize = 12.sp,
                    color = TextGray
                )
                Text(
                    text = "50tr",
                    fontSize = 12.sp,
                    color = TextGray
                )
            }
        }
    }
}

private fun formatCurrency(amount: Long): String {
    return NumberFormat.getNumberInstance(Locale("vi", "VN")).format(amount)
}

// ==================== Interest & Duration Row ====================

@Composable
fun InterestDurationRow(
    interestRate: String,
    onInterestChange: (String) -> Unit,
    duration: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Interest Rate Card
        Card(
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = CardBackground)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "Lãi suất",
                    fontSize = 12.sp,
                    color = TextGray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = interestRate,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )
                    Text(
                        text = " %",
                        fontSize = 16.sp,
                        color = TextGray,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }
            }
        }
        
        // Duration Card
        Card(
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = CardBackground)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "Thời hạn",
                    fontSize = 12.sp,
                    color = TextGray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = duration,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
            }
        }
    }
}

// ==================== Duration Chips ====================

@Composable
fun DurationChipsRow(
    selectedDuration: Int,
    onDurationSelect: (Int) -> Unit
) {
    val durations = listOf(3, 6, 9, 12)
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        durations.forEach { months ->
            val isSelected = selectedDuration == months
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(20.dp))
                    .clickable { onDurationSelect(months) },
                shape = RoundedCornerShape(20.dp),
                color = if (isSelected) CyanButton else Color.Transparent,
                border = if (!isSelected) {
                    ButtonDefaults.outlinedButtonBorder
                } else null
            ) {
                Text(
                    text = "$months tháng",
                    fontSize = 13.sp,
                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                    color = if (isSelected) Color.White else TextGray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            }
        }
    }
}

// ==================== Loan Purpose Section ====================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoanPurposeSection(
    selectedPurpose: String,
    onPurposeChange: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit
) {
    val purposes = listOf("Kinh doanh", "Tiêu dùng", "Giáo dục", "Y tế", "Khác")
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Mục đích vay",
            fontSize = 14.sp,
            color = TextGray
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = onExpandedChange
        ) {
            OutlinedTextField(
                value = selectedPurpose,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = CardBackground,
                    unfocusedContainerColor = CardBackground,
                    focusedBorderColor = CyanButton,
                    unfocusedBorderColor = Color(0xFF374151),
                    focusedTextColor = TextWhite,
                    unfocusedTextColor = TextWhite
                ),
                shape = RoundedCornerShape(12.dp)
            )
            
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { onExpandedChange(false) },
                modifier = Modifier.background(CardBackground)
            ) {
                purposes.forEach { purpose ->
                    DropdownMenuItem(
                        text = { Text(purpose, color = TextWhite) },
                        onClick = {
                            onPurposeChange(purpose)
                            onExpandedChange(false)
                        },
                        modifier = Modifier.background(CardBackground)
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        OutlinedTextField(
            value = description,
            onValueChange = onDescriptionChange,
            placeholder = {
                Text(
                    "Mô tả chi tiết mục đích vay (không bắt buộc)...",
                    color = TextGray,
                    fontSize = 14.sp
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = CardBackground,
                unfocusedContainerColor = CardBackground,
                focusedBorderColor = CyanButton,
                unfocusedBorderColor = Color(0xFF374151),
                focusedTextColor = TextWhite,
                unfocusedTextColor = TextWhite,
                cursorColor = CyanButton
            ),
            shape = RoundedCornerShape(12.dp)
        )
    }
}

// ==================== Collateral Section ====================

@Composable
fun CollateralSection(
    selectedType: String,
    onTypeSelect: (String) -> Unit,
    contractAddress: String,
    onContractAddressChange: (String) -> Unit,
    tokenId: String,
    onTokenIdChange: (String) -> Unit,
    selectedNetwork: String,
    onNetworkChange: (String) -> Unit,
    networkExpanded: Boolean,
    onNetworkExpandedChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        // Header Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Tài sản thế chấp",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextWhite
            )
            
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = CyanButton.copy(alpha = 0.15f)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
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
                        text = "Smart Contract",
                        fontSize = 11.sp,
                        color = CyanButton
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // Type Selection
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CollateralTypeCard(
                icon = Icons.Default.Diamond,
                label = "NFT",
                isSelected = selectedType == "NFT",
                onClick = { onTypeSelect("NFT") },
                modifier = Modifier.weight(1f)
            )
            CollateralTypeCard(
                icon = Icons.Default.CurrencyBitcoin,
                label = "Crypto",
                isSelected = selectedType == "Crypto",
                onClick = { onTypeSelect("Crypto") },
                modifier = Modifier.weight(1f)
            )
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // Select from Wallet Button
        OutlinedButton(
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = CyanButton
            ),
            border = ButtonDefaults.outlinedButtonBorder.copy(
                brush = androidx.compose.ui.graphics.SolidColor(CyanButton)
            )
        ) {
            Icon(
                imageVector = Icons.Default.AccountBalanceWallet,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Chọn từ Ví NFT của bạn",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Manual Input Section
        Text(
            text = "HOẶC NHẬP THỦ CÔNG",
            fontSize = 11.sp,
            color = TextGray,
            letterSpacing = 1.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // Contract Address
        Text(
            text = "Địa chỉ Contract (Address)",
            fontSize = 12.sp,
            color = TextGray
        )
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = contractAddress,
            onValueChange = onContractAddressChange,
            placeholder = { Text("0x...", color = TextGray) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = CardBackground,
                unfocusedContainerColor = CardBackground,
                focusedBorderColor = CyanButton,
                unfocusedBorderColor = Color(0xFF374151),
                focusedTextColor = TextWhite,
                unfocusedTextColor = TextWhite,
                cursorColor = CyanButton
            ),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.QrCodeScanner,
                        contentDescription = "Scan",
                        tint = TextGray
                    )
                }
            }
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // Token ID & Network Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Token ID",
                    fontSize = 12.sp,
                    color = TextGray
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = tokenId,
                    onValueChange = onTokenIdChange,
                    placeholder = { Text("#ID", color = TextGray) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = CardBackground,
                        unfocusedContainerColor = CardBackground,
                        focusedBorderColor = CyanButton,
                        unfocusedBorderColor = Color(0xFF374151),
                        focusedTextColor = TextWhite,
                        unfocusedTextColor = TextWhite,
                        cursorColor = CyanButton
                    ),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Mạng lưới",
                    fontSize = 12.sp,
                    color = TextGray
                )
                Spacer(modifier = Modifier.height(6.dp))
                NetworkDropdown(
                    selectedNetwork = selectedNetwork,
                    expanded = networkExpanded,
                    onExpandedChange = onNetworkExpandedChange,
                    onNetworkChange = onNetworkChange
                )
            }
        }
    }
}

@Composable
private fun CollateralTypeCard(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .then(
                if (isSelected) Modifier.border(2.dp, CyanButton, RoundedCornerShape(12.dp))
                else Modifier
            ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) CyanButton.copy(alpha = 0.1f) else CardBackground
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isSelected) CyanButton else TextGray,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = label,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = if (isSelected) CyanButton else TextWhite
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NetworkDropdown(
    selectedNetwork: String,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onNetworkChange: (String) -> Unit
) {
    val networks = listOf("Ethereum", "BSC", "Polygon", "Solana")
    
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = onExpandedChange
    ) {
        OutlinedTextField(
            value = selectedNetwork,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = CardBackground,
                unfocusedContainerColor = CardBackground,
                focusedBorderColor = CyanButton,
                unfocusedBorderColor = Color(0xFF374151),
                focusedTextColor = TextWhite,
                unfocusedTextColor = TextWhite
            ),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )
        
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) },
            modifier = Modifier.background(CardBackground)
        ) {
            networks.forEach { network ->
                DropdownMenuItem(
                    text = { Text(network, color = TextWhite) },
                    onClick = {
                        onNetworkChange(network)
                        onExpandedChange(false)
                    },
                    modifier = Modifier.background(CardBackground)
                )
            }
        }
    }
}

// ==================== Loan Summary Section ====================

@Composable
fun LoanSummarySection(
    monthlyPayment: String,
    totalInterest: String,
    totalRepayment: String
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
                text = "Tóm tắt khoản vay",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextWhite
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            SummaryRow(label = "Ước tính trả hàng tháng", value = monthlyPayment)
            Spacer(modifier = Modifier.height(8.dp))
            SummaryRow(label = "Tổng lãi phải trả", value = totalInterest)
            Spacer(modifier = Modifier.height(8.dp))
            SummaryRow(label = "Tổng số tiền hoàn trả", value = totalRepayment, highlight = true)
        }
    }
}

@Composable
private fun SummaryRow(
    label: String,
    value: String,
    highlight: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 13.sp,
            color = TextGray
        )
        Text(
            text = value,
            fontSize = 13.sp,
            fontWeight = if (highlight) FontWeight.Bold else FontWeight.Medium,
            color = if (highlight) CyanButton else TextWhite
        )
    }
}

// ==================== Submit Button ====================

@Composable
fun SubmitLoanButton(
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(52.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = CyanButton,
            disabledContainerColor = CyanButton.copy(alpha = 0.5f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = "Gửi Yêu Cầu",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
