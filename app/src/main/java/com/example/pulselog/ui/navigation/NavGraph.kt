package com.example.pulselog.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pulselog.ui.screens.*
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding

sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard")
    object Workouts : Screen("workouts")
    object Analytics : Screen("analytics")
    object Routines : Screen("routines")
    object Profile : Screen("profile")
    object ActiveWorkout : Screen("active_workout")  // 👈 add this
}

@Composable
fun NavGraph(navController: NavHostController, padding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route,
        modifier = Modifier.padding(padding)
    ) {
        composable(Screen.Dashboard.route) { DashboardScreen(navController) }  // 👈 pass navController
        composable(Screen.Workouts.route) { WorkoutsScreen(navController) }    // 👈 pass navController
        composable(Screen.Analytics.route) { AnalyticsScreen() }
        composable(Screen.Routines.route) { RoutinesScreen() }
        composable(Screen.Profile.route) { ProfileScreen() }
        composable(Screen.ActiveWorkout.route) {                               // 👈 add this
            ActiveWorkoutScreen(onFinish = { navController.popBackStack() })
        }
    }
}