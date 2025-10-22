package com.example.base_app.presentation.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.base_app.presentation.ui.screens.auth.LoginScreen
import com.example.base_app.presentation.ui.screens.auth.RegisterScreen
import com.example.base_app.presentation.ui.screens.home.HomeScreen
import com.example.base_app.presentation.ui.screens.onboarding.OnboardingScreen
import com.example.base_app.presentation.viewmodel.HomeViewModel

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    Log.d("SetupNavGraph", "Initializing navigation graph")
    val homeViewModel: HomeViewModel = hiltViewModel()
    Log.d("SetupNavGraph", "HomeViewModel initialized")
    val userState = homeViewModel.currentUser.collectAsState()
    val currentUser = userState.value
    Log.d("SetupNavGraph", "Current user: $currentUser")

    NavHost(
        navController = navController,
        startDestination = if (currentUser != null) "home" else "onboarding"
    ) {
        composable("onboarding") {
            Log.d("SetupNavGraph", "Navigating to OnboardingScreen")
            OnboardingScreen(navController)
        }
        composable("login") {
            Log.d("SetupNavGraph", "Navigating to LoginScreen")
            LoginScreen(navController)
        }
        composable("register") {
            Log.d("SetupNavGraph", "Navigating to RegisterScreen")
            RegisterScreen(navController)
        }
        composable("home") {
            Log.d("SetupNavGraph", "Navigating to HomeScreen")
            HomeScreen()
        }
    }
}