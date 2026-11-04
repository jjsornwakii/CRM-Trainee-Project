package com.example.mywallet.ui.theme.screens.addtransaction

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// app data state
data class TransactionUiModel(
    val id: Long = System.currentTimeMillis(),
    val type: String = "Expense",
    val amount: String  = "",
    val category: String = "",
    val expanded: Boolean = false,
    val showDatePicker: Boolean =  false,
    val note: String = "",
    val date: String = SimpleDateFormat(
        "dd/MM/yyyy",
        Locale.getDefault()
    ).format(Date())
)