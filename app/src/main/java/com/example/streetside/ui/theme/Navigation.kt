package com.example.streetside.ui.theme

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.streetside.model.SharedViewModel
import com.example.streetside.ui.theme.screens.HomeScreen
import com.example.streetside.ui.theme.screens.LoginScreen
import com.example.streetside.ui.theme.screens.MenuScreen
import com.example.streetside.ui.theme.screens.OrderScreen
import com.example.streetside.ui.theme.screens.RatingScreen
import com.example.streetside.ui.theme.screens.RegisterScreen
import com.example.streetside.ui.theme.screens.SchoolScreen
import com.example.streetside.ui.theme.screens.SplashScreen
import com.example.streetside.ui.theme.screens.VendorScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel: SharedViewModel = viewModel()

    NavHost(navController = navController, startDestination = "splash") {
        composable(route = "splash") {
            SplashScreen(navController = navController)
        }
        composable(route = "home") {
            HomeScreen(navController = navController)
        }
        composable(route = "register") {
            RegisterScreen(navController = navController)
        }
        composable(route = "login") {
            LoginScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = "school") {
            SchoolScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = "vendor") {
            VendorScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = "menu") {
            MenuScreen(navController = navController, viewModel = viewModel)
        }
        composable("order?showPrompt={showPrompt}") { backStackEntry ->
            val showPrompt = backStackEntry.arguments?.getString("showPrompt")?.toBoolean() ?: false
            OrderScreen(navController = navController, viewModel = viewModel, showPrompt = showPrompt)
        }
        composable(route = "rate") {
            RatingScreen(navController = navController, viewModel = viewModel)
        }
    }
}





