package com.example.mywallet.data.repository

import com.example.mywallet.data.local.ExpenseDao
import com.example.mywallet.data.local.ExpenseEntity
import kotlinx.coroutines.flow.Flow

class ExpenseRepository (
    private val expenseDao: ExpenseDao
){
    val allTransactions: Flow<List<ExpenseEntity>> =
        expenseDao.getAllTransactions()

    suspend fun insertTransaction(
        transaction: ExpenseEntity
    ){
        expenseDao.insertTransaction(transaction)
    }

    suspend fun updateTransaction(
        transaction: ExpenseEntity
    ){
        expenseDao.updateTransaction(transaction)
    }

    suspend fun deleteTransaction(id:  Long){
        expenseDao.deleteTransactionById(id)
    }

    suspend fun deleteAllTransactions(){
        expenseDao.deleteAllTransactions()
    }
}