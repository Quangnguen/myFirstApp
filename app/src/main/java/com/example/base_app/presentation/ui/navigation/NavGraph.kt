package com.example.base_app.presentation.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.base_app.presentation.ui.screens.auth.LoginScreen
import com.example.base_app.presentation.ui.screens.auth.RegisterScreen
import com.example.base_app.presentation.ui.screens.home.HomeScreen
import com.example.base_app.presentation.ui.screens.loans.ConfirmLoanRequestScreen
import com.example.base_app.presentation.ui.screens.loans.CreateLoanScreen
import com.example.base_app.presentation.ui.screens.loans.BrowseLoansScreen
import com.example.base_app.presentation.ui.screens.loans.ConfirmTransactionScreen
import com.example.base_app.presentation.ui.screens.loans.LoanDetailScreen
import com.example.base_app.presentation.ui.screens.loans.LoanRequestSuccessScreen
import com.example.base_app.presentation.ui.screens.loans.LoansScreen
import com.example.base_app.presentation.ui.screens.onboarding.OnboardingScreen
import com.example.base_app.presentation.ui.screens.profile.ProfileScreen
import com.example.base_app.presentation.ui.screens.wallet.WalletScreen
import com.example.base_app.presentation.viewmodel.HomeViewModel

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    Log.d("AppNavigation", "Initializing navigation graph")
    val homeViewModel: HomeViewModel = hiltViewModel()
    Log.d("AppNavigation", "HomeViewModel initialized")
    
    
    val userState = homeViewModel.currentUser.collectAsState()
    val currentUser = userState.value
    Log.d("AppNavigation", "Current user: $currentUser")

    // Logic: Nếu đã đăng nhập -> Home, chưa -> Onboarding (hoặc Login tùy bạn chọn)
    // Trong MainActivity cũ logic là: if (currentUser != null) "onboarding" else "login" (nhưng do lỗi check State nên luôn vào onboarding)
    // Ở đây tôi để logic chuẩn: Có user -> Home, Không -> Onboarding
    val startDest = if (currentUser != null) "home" else "onboarding"
    val currentRoute = remember { mutableStateOf("home") }

    NavHost(
        navController = navController,
        startDestination = startDest
    ) {
        composable("onboarding") {
            Log.d("AppNavigation", "Navigating to OnboardingScreen")
            OnboardingScreen(navController)
        }
        composable("login") {
            Log.d("AppNavigation", "Navigating to LoginScreen")
            LoginScreen(navController)
        }
        composable("register") {
            Log.d("AppNavigation", "Navigating to RegisterScreen")
            RegisterScreen(navController)
        }
        composable("home") {
            currentRoute.value = "home"
            Log.d("AppNavigation", "Navigating to HomeScreen")
            HomeScreen(navController = navController)
        }
         composable("loans") {
            currentRoute.value = "loans"
            Log.d("AppNavigation", "Navigating to LoansScreen")
            LoansScreen(navController = navController)
        }
        composable("profile") {
            currentRoute.value = "profile"
            Log.d("AppNavigation", "Navigating to ProfileScreen")
            ProfileScreen(navController = navController)
        }
        composable("browse_loans") {
            currentRoute.value = "browse_loans"
            Log.d("AppNavigation", "Navigating to BrowseLoansScreen")
            BrowseLoansScreen(navController = navController)
        }
        composable("loan_detail/{loanId}") { backStackEntry ->
            val loanId = backStackEntry.arguments?.getString("loanId")
            currentRoute.value = "loan_detail"
            Log.d("AppNavigation", "Navigating to LoanDetailScreen with loanId: $loanId")
            LoanDetailScreen(navController = navController, loanId = loanId)
        }
        composable("confirm_transaction/{loanId}") { backStackEntry ->
            val loanId = backStackEntry.arguments?.getString("loanId")
            currentRoute.value = "confirm_transaction"
            Log.d("AppNavigation", "Navigating to ConfirmTransactionScreen with loanId: $loanId")
            ConfirmTransactionScreen(navController = navController, loanId = loanId)
        }
        composable("create_loan") {
            currentRoute.value = "create_loan"
            Log.d("AppNavigation", "Navigating to CreateLoanScreen")
            CreateLoanScreen(navController = navController)
        }
        composable("confirm_loan_request") {
            currentRoute.value = "confirm_loan_request"
            Log.d("AppNavigation", "Navigating to ConfirmLoanRequestScreen")
            ConfirmLoanRequestScreen(navController = navController)
        }
        composable("loan_request_success") {
            currentRoute.value = "loan_request_success"
            Log.d("AppNavigation", "Navigating to LoanRequestSuccessScreen")
            LoanRequestSuccessScreen(navController = navController)
        }
        composable("wallet") {
            currentRoute.value = "wallet"
            Log.d("AppNavigation", "Navigating to WalletScreen")
            WalletScreen(navController = navController)
        }
    }
}