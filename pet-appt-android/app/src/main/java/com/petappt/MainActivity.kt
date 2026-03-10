package com.petappt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.petappt.ui.screens.*
import com.petappt.ui.theme.PetApptTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetApptTheme {
                PetAppNavigation()
            }
        }
    }
}

@Composable
fun PetAppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { 
            LoginRegisterScreen(onLoginSuccess = { role ->
                if (role == "ADMIN") navController.navigate("admin_panel")
                else navController.navigate("owner_dashboard")
            })
        }
        composable("owner_dashboard") { 
            OwnerDashboardScreen(
                onBookAppointment = { navController.navigate("book_appointment") },
                onLogout = { navController.navigate("login") }
            ) 
        }
        composable("book_appointment") { 
            BookAppointmentScreen(onSuccess = { navController.popBackStack() }) 
        }
        composable("admin_panel") { 
            AdminPanelScreen(onLogout = { navController.navigate("login") }) 
        }
    }
}
