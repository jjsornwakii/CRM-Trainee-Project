package com.example.mywallet.ui.theme.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywallet.ui.theme.FinanceBackground
import com.example.mywallet.ui.theme.FinanceTextPrimary

@Composable
fun DashboardScreen(){
    Column (
        modifier = Modifier.fillMaxSize().padding(16.dp).background(FinanceBackground).padding(20.dp)
    ){
//        Header
        Text(
            text = "My Wallet",
            color = FinanceTextPrimary,
            fontSize = 26.sp
        )
    }
}