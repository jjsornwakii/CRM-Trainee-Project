package com.example.mywallet.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavHost
import androidx.navigation.compose.composable
import com.example.mywallet.ui.theme.screens.dashboard.DashboardScreen
import com.example.mywallet.ui.theme.screens.settings.SettingsScreen
import com.example.mywallet.ui.theme.screens.statistics.StatisticsScreen
import com.example.mywallet.ui.theme.screens.transactions.HistoryScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier
){
    NavHost(
        navController = navController,
        startDestination = Screen.HOME,
        modifier = modifier
    ){
        composable(Screen.HOME){
            DashboardScreen()
        }

        composable(Screen.HISTORY){
            HistoryScreen()

        }

        composable(Screen.STATISTICS){
            StatisticsScreen()
        }

        composable(Screen.SETTINGS){
            SettingsScreen()
        }

    }
}