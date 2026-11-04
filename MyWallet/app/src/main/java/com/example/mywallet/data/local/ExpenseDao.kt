package com.example.mywallet.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao{
    @Query("SELECT *  FROM transactions ORDER BY id DESC")
    fun getAllTransactions(): Flow<List<ExpenseEntity>>

    @Insert
    suspend fun insertTransaction(
        transaction: ExpenseEntity
    )

    @Update
    suspend fun updateTransaction(
        transaction: ExpenseEntity
    )

    @Delete
    suspend fun deleteTransaction(
        transaction: ExpenseEntity
    )

    @Query("DELETE FROM  transactions  WHERE id = :id")
    suspend fun deleteTransactionById(id: Long)

    @Query("DELETE FROM transactions")
    suspend fun deleteAllTransactions()
}