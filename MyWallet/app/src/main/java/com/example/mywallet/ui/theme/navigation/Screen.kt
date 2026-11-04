package com.example.mywallet.ui.theme.navigation

object Screen{
    const val HOME = "home"
    const val HISTORY = "history"
    const val STATISTICS = "statistics"
    const val SETTINGS = "settings"
    const val BUDGETS = "Budgets"
    const val ADD_TRANSACTION = "add_transaction"

    const val EDIT_TRANSACTION = "edit_transaction/{transactionId}"

    fun editTransaction(transactionId: Long): String{
        return "edit_transaction/$transactionId"
    }

}