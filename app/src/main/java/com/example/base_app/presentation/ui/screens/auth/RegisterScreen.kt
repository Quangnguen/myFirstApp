package com.example.base_app.presentation.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.base_app.presentation.ui.components.AuthButton
import com.example.base_app.presentation.ui.components.AuthTextField
import com.example.base_app.presentation.ui.theme.PrimaryBlue
import com.example.base_app.presentation.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var fullNameInteracted by remember { mutableStateOf(false) }
    var emailInteracted by remember { mutableStateOf(false) }
    var passwordInteracted by remember { mutableStateOf(false) }
    var confirmPasswordInteracted by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val fullNameError by remember(fullName, fullNameInteracted) {
        derivedStateOf {
            if (fullNameInteracted && fullName.isBlank()) "Họ tên không được để trống"
            else null
        }
    }

    val emailError by remember(email, emailInteracted) {
        derivedStateOf {
            if (emailInteracted && email.isBlank()) "Email không được để trống"
            else if (emailInteracted && !email.contains("@")) "Email không hợp lệ"
            else null
        }
    }

    val passwordError by remember(password, passwordInteracted) {
        derivedStateOf {
            if (passwordInteracted && password.isBlank()) "Mật khẩu không được để trống"
            else if (passwordInteracted && password.length < 6) "Mật khẩu phải có ít nhất 6 ký tự"
            else null
        }
    }

    val confirmPasswordError by remember(confirmPassword, confirmPasswordInteracted, password) {
        derivedStateOf {
            if (confirmPasswordInteracted && confirmPassword.isBlank()) "Vui lòng xác nhận mật khẩu"
            else if (confirmPasswordInteracted && confirmPassword != password) "Mật khẩu không khớp"
            else null
        }
    }

    LaunchedEffect(uiState.success) {
        if (uiState.success) {
            navController.navigate("home") {
                popUpTo("register") { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.Start)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = PrimaryBlue
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Tạo tài khoản mới",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = PrimaryBlue,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(48.dp))

            AuthTextField(
                value = fullName,
                onValueChange = {
                    fullName = it
                    fullNameInteracted = true
                    viewModel.clearError()
                },
                label = "Họ và tên",
                error = fullNameError,
//                autoFocus = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            AuthTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailInteracted = true
                    viewModel.clearError()
                },
                label = "Email",
                keyboardType = KeyboardType.Email,
                error = emailError
            )

            Spacer(modifier = Modifier.height(16.dp))

            AuthTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordInteracted = true
                    viewModel.clearError()
                },
                label = "Mật khẩu",
                isPassword = true,
                keyboardType = KeyboardType.Password,
                error = passwordError
            )

            Spacer(modifier = Modifier.height(16.dp))

            AuthTextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    confirmPasswordInteracted = true
                    viewModel.clearError()
                },
                label = "Xác nhận mật khẩu",
                isPassword = true,
                keyboardType = KeyboardType.Password,
                error = confirmPasswordError
            )

            Spacer(modifier = Modifier.height(24.dp))

            uiState.error?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            AuthButton(
                text = "Đăng ký",
                onClick = {
                    fullNameInteracted = true
                    emailInteracted = true
                    passwordInteracted = true
                    confirmPasswordInteracted = true
                    if (fullNameError == null && emailError == null &&
                        passwordError == null && confirmPasswordError == null) {
                        scope.launch {
                            viewModel.register(email, fullName, password)
                        }
                    }
                },
                isLoading = uiState.isLoading,
                enabled = fullName.isNotBlank() && email.isNotBlank() &&
                        password.isNotBlank() && confirmPassword.isNotBlank()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Đã có tài khoản? ")
                TextButton(onClick = { navController.navigate("login") }) {
                    Text("Đăng nhập", color = PrimaryBlue)
                }
            }
        }
    }
}