package com.example.mywallet.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywallet.data.local.ExpenseDatabase
import com.example.mywallet.data.local.ExpenseEntity
import com.example.mywallet.data.repository.ExpenseRepository
import com.example.mywallet.ui.theme.screens.addtransaction.TransactionUiModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ExpenseViewModel(application: Application): AndroidViewModel(application){

    private val database  = ExpenseDatabase.getDatabase(application)

    private val repository = ExpenseRepository(database.expenseDao())


    val transactions: StateFlow<List<TransactionUiModel>> = repository.allTransactions.map {
        entities ->  entities.map {
            entity ->
        TransactionUiModel(
            id = entity.id,
            type = entity.type,
            amount = entity.amount.toString(),
            category = entity.category,
            note = entity.note,
            date  = entity.date
        )
    }
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addTransaction(transaction: TransactionUiModel){
        viewModelScope.launch {
            repository.insertTransaction(
                ExpenseEntity(
                    type = transaction.type,
                    amount = transaction.amount.toDoubleOrNull()?: 0.0,
                    category = transaction.category,
                    note = transaction.note,
                    date = transaction.date
                )
            )
        }
    }

    fun  updateTransaction(transaction: TransactionUiModel){
        viewModelScope.launch {
            repository.updateTransaction(
                ExpenseEntity(
                    id = transaction.id,
                    type = transaction.type,
                    amount = transaction.amount.toDoubleOrNull() ?: 0.0,
                    category = transaction.category,
                    note = transaction.note,
                    date = transaction.date
                )
            )
        }
    }

    fun deleteTransaction(id:Long){
        viewModelScope.launch {
            repository.deleteTransaction(id)
        }
    }

    fun  deleteAllTransactions(){
        viewModelScope.launch {
            repository.deleteAllTransactions()
        }
    }


    fun getTotalIncome():Double {
        return transactions.value.filter { it.type  == "Income" }
            .sumOf { it.amount.toDoubleOrNull() ?: 0.0 }
    }

    fun getTotalExpense(): Double {
        return  transactions.value.filter { it.type == "Expense" }
            .sumOf { it.amount.toDoubleOrNull() ?: 0.0 }
    }

    fun getBalance(): Double {
        return getTotalIncome()  -  getTotalExpense()
    }

    fun  getTransactionCount(): Int {
        return transactions.value.size
    }
}