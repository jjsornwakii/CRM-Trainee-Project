package com.example.mywallet.ui.theme.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.mywallet.ui.theme.FinanceBackground
import com.example.mywallet.ui.theme.FinanceBorder
import com.example.mywallet.ui.theme.FinancePrimary
import com.example.mywallet.ui.theme.FinanceSurface
import com.example.mywallet.ui.theme.FinanceTextSecondary
import com.example.mywallet.viewmodel.ExpenseViewModel


data class BottomNavItem(
    val title: String,
    val route: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Composable
fun  MainScreen(){

    val navController = rememberNavController()

    val expenseViewModel: ExpenseViewModel = viewModel()
    val transactions by expenseViewModel.transactions.collectAsStateWithLifecycle()

    val items = listOf(
        BottomNavItem("Home", Screen.HOME,Icons.Default.Home),
        BottomNavItem("History", Screen.HISTORY,Icons.Default.ReceiptLong),
        BottomNavItem("Statistics", Screen.STATISTICS,Icons.Default.BarChart),
        BottomNavItem("Settings", Screen.SETTINGS,Icons.Default.Settings),
        BottomNavItem("Budgets", Screen.BUDGETS, Icons.Default.CurrencyBitcoin)
    )

    val navBackStackEntry  by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        containerColor = FinanceBackground,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.ADD_TRANSACTION)
                },
                containerColor = FinancePrimary,
                contentColor = FinanceBackground
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add  Transaction"
                )
            }
        },
        bottomBar = {
            NavigationBar(
                containerColor = FinanceSurface
            ) {
                items.forEach { item ->
//                    it = ตัว currentDestination.hierarchy.any (it) คือค่าของตัว Destination เอง
                    val selected = currentDestination?.hierarchy?.any {it.route ==  item.route} == true

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(item.route){
                                popUpTo(
                                    navController.graph.findStartDestination().id
                                ){
                                    saveState  = true
                                }

                                launchSingleTop = true
                                restoreState = true
                            }
                        },

                        icon =  {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title
                            )
                        },

                        label = {
                            Text(item.title)
                        },

                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = FinancePrimary,
                            selectedTextColor = FinancePrimary,
                            unselectedIconColor = FinanceTextSecondary,
                            unselectedTextColor = FinanceTextSecondary,
                            indicatorColor = FinanceBorder
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        AppNavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            onSaveTransaction = {
                transaction  -> expenseViewModel.addTransaction(transaction)
                navController.navigate(Screen.HISTORY)
            },
            transactions = transactions,
            onDeleteTransaction = {
                id ->
                expenseViewModel.deleteTransaction(id)
            },
            expenseViewModel = expenseViewModel,
            onUpdateTransaction = {
                transaction ->  expenseViewModel.updateTransaction(transaction)
            }
        )

    }
}