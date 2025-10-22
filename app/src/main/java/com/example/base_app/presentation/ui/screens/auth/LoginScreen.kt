package com.example.base_app.presentation.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailInteracted by remember { mutableStateOf(false) }
    var passwordInteracted by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

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

    LaunchedEffect(uiState.success) {
        if (uiState.success) {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
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
            Text(
                text = "Đăng nhập",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = PrimaryBlue
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Vui lòng nhập thông tin đăng nhập",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(48.dp))

            AuthTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailInteracted = true
                    viewModel.clearError()
                },
                label = "Email",
                keyboardType = KeyboardType.Email,
                error = emailError,
//                autoFocus = true
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
                error = passwordError,
//                autoFocus = false
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
                text = "Đăng nhập",
                onClick = {
                    emailInteracted = true
                    passwordInteracted = true
                    if (emailError == null && passwordError == null) {
                        scope.launch {
                            viewModel.login(email, password)
                        }
                    }
                },
                isLoading = uiState.isLoading,
                enabled = email.isNotBlank() && password.isNotBlank()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                onClick = { /* TODO: Forgot Password */ },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Quên mật khẩu?", color = PrimaryBlue)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Chưa có tài khoản? ")
                TextButton(onClick = { navController.navigate("register") }) {
                    Text("Đăng ký", color = PrimaryBlue)
                }
            }
        }
    }
}