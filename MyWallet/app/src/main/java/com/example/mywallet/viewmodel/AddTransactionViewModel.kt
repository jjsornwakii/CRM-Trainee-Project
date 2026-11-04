
package com.example.mywallet.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.mywallet.ui.theme.screens.addtransaction.TransactionUiModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TransactionViewModel : ViewModel() {

    var uiState by mutableStateOf(TransactionUiModel())
        private set

    fun updateAmount(value: String) {
        uiState = uiState.copy(
            amount = value
        )
    }

    fun updateType(value: String) {
        uiState = uiState.copy(
            type = value,
            category = ""
        )
    }

    fun updateCategory(value: String) {
        uiState = uiState.copy(
            category = value
        )
    }

    fun updateNote(value: String) {
        uiState = uiState.copy(
            note = value
        )
    }

    fun updateDate(value: String) {
        uiState = uiState.copy(
            date = value
        )
    }

    fun updateShowDatePicker(value: Boolean){
        uiState =  uiState.copy(
            showDatePicker = !value
        )
    }

    fun  updateExpanded(value: Boolean){
        uiState = uiState.copy(
            expanded = !value
        )
    }

    fun onResetStatevalue(){
        uiState = uiState.copy(
             note = "",
            amount = "",
            category = "",
            date = SimpleDateFormat(
                "dd/MM/yyyy",
                Locale.getDefault()
            ).format(Date()),
            type = "Expense"
        )
    }

    val categories: List<String>
        get() = if (uiState.type == "Income") {
            listOf("Salary", "Freelance", "Investment", "Gift", "Other")
        } else {
            listOf("Food", "Transport", "Shopping", "Bills",
                "Entertainment", "Health","Travel","Education", "Other")
        }

    val validAmount: Boolean
        get() = uiState.amount.toDoubleOrNull()?.let { it > 0 } ?: false
}
