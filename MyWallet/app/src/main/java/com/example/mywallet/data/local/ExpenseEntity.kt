package com.example.mywallet.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class ExpenseEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long  = 0,

    val type:  String,

    val amount:  Double,

    val category: String,

    val note:  String,

    val date: String
)