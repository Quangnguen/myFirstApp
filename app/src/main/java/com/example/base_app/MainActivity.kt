//package com.example.base_app
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import com.example.base_app.presentation.ui.theme.Base_appTheme
//import androidx.compose.material3.Surface
//import com.example.base_app.presentation.ui.navigation.SetupNavGraph
//import androidx.compose.material3.MaterialTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            Base_appTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    SetupNavGraph()
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    Base_appTheme {
//        Greeting("Android")
//    }
//}

package com.example.base_app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.base_app.presentation.ui.screens.auth.LoginScreen
import com.example.base_app.presentation.ui.screens.auth.RegisterScreen
import com.example.base_app.presentation.ui.screens.home.HomeScreen
import com.example.base_app.presentation.ui.screens.onboarding.OnboardingScreen
import com.example.base_app.presentation.ui.theme.Base_appTheme
import com.example.base_app.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate called")
        setContent {
            Base_appTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Log.d("MainActivity", "Setting up AppNavigation")
                    AppNavigation()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume called")
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    Log.d("AppNavigation", "Creating NavController")
    val homeViewModel: HomeViewModel = hiltViewModel()
    Log.d("AppNavigation", "HomeViewModel initialized")
    val currentUser = homeViewModel.currentUser.collectAsState()
    Log.d("AppNavigation", "Current user: $currentUser")

    NavHost(
        navController = navController,
        startDestination = if (currentUser != null) "onboarding" else "login"
    ) {
        composable ("onboarding") {
            OnboardingScreen(navController = navController)
        }
        composable("login") {
            Log.d("AppNavigation", "Navigating to LoginScreen")
            LoginScreen(navController = navController)
        }
        composable("register") {
            Log.d("AppNavigation", "Navigating to RegisterScreen")
            RegisterScreen(navController = navController)
        }
        composable("home") {
            Log.d("AppNavigation", "Navigating to HomeScreen")
            HomeScreen()
        }
    }
}