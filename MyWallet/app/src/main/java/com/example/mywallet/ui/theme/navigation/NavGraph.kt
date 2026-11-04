package com.example.mywallet.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mywallet.ui.theme.screens.addtransaction.AddTransactionScreen
import com.example.mywallet.ui.theme.screens.addtransaction.TransactionUiModel
import com.example.mywallet.ui.theme.screens.budgets.BudgetsScreen
import com.example.mywallet.ui.theme.screens.dashboard.DashboardScreen
import com.example.mywallet.ui.theme.screens.settings.SettingsScreen
import com.example.mywallet.ui.theme.screens.statistics.StatisticsScreen
import com.example.mywallet.ui.theme.screens.transactions.HistoryScreen
import com.example.mywallet.viewmodel.ExpenseViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier,
    transactions:List<TransactionUiModel>,
    onSaveTransaction:(TransactionUiModel) -> Unit,
    onDeleteTransaction:(Long) -> Unit,
    expenseViewModel: ExpenseViewModel,
    onUpdateTransaction:(TransactionUiModel) -> Unit
){
    NavHost(
        navController = navController,
        startDestination = Screen.HOME,
        modifier = modifier
    ){
        composable(Screen.HOME){
            DashboardScreen(
                expenseViewModel = expenseViewModel
            )
        }

        composable(Screen.HISTORY){
            HistoryScreen(
                transactions  = transactions,
                onDeleteTransaction = onDeleteTransaction,
                onEditTransaction  = {
                    transactionId ->
                    navController.navigate("edit_transaction/$transactionId")
                }
            )

        }

        composable(Screen.STATISTICS){
            StatisticsScreen()
        }

        composable(Screen.SETTINGS){
            SettingsScreen()
        }

        composable (Screen.BUDGETS){
            BudgetsScreen()
        }

        composable (Screen.ADD_TRANSACTION){
            AddTransactionScreen(
                onSave = onSaveTransaction,
                onCancel = {navController.navigate(Screen.HOME)}
            )
        }

        composable(
            route =  "edit_transaction/{transactionId}",
            arguments = listOf(
                navArgument("transactionId"){
                    type = NavType.LongType
                }
            )
        ) {
            backStackEntry ->
            val transactionId =  backStackEntry.arguments?.getLong("transactionId")
            val transaction =  transactions.find {
                it.id  == transactionId
            }

            if(transaction  != null){
                AddTransactionScreen(
                    initailTransaction = transaction,
                    onSave = {
                        updatedTransaction ->
                        onUpdateTransaction(updatedTransaction)
                        navController.popBackStack()
                    },
                    onCancel = {
                        navController.popBackStack()
                    }
                )
            }
        }

    }
}