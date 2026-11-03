package com.example.mywallet.ui.theme.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywallet.ui.theme.FinanceBackground
import com.example.mywallet.ui.theme.FinanceExpense
import com.example.mywallet.ui.theme.FinancePrimary
import com.example.mywallet.ui.theme.FinanceSurface
import com.example.mywallet.ui.theme.FinanceTextPrimary
import com.example.mywallet.ui.theme.FinanceTextSecondary

@Composable
fun DashboardScreen(){
    Column (
        modifier = Modifier.fillMaxSize().padding(16.dp).background(FinanceBackground).padding(20.dp)
    ){
//        Header
        Text(
            text = "Dashboard",
            color = FinanceTextPrimary,
            fontSize = 26.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Monthly Overview",
            color = FinanceTextSecondary,
            fontSize = 12.sp

        )

        Spacer(Modifier.height(24.dp))

//        Balance Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = FinanceSurface
            )
        ) {
            Column (modifier = Modifier.padding(20.dp)){
                Text(
                    text = "Total Balance",
                    color = FinanceTextSecondary
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "$50,000.00",
                    color = FinancePrimary,
                    fontSize = 32.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Your avaliable balance",
                    color = FinanceTextSecondary,
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

//        Income and Expense
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ){
            Card (
                modifier = Modifier.weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = FinanceSurface
                )
            ){
                Column (modifier = Modifier.padding(16.dp)){
                    Text(
                        text = "Total Income",color = FinanceTextSecondary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "+$35,000",
                        color = FinancePrimary,
                        fontSize = 18.sp
                    )
                }
            }

            Card (modifier = Modifier.weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = FinanceSurface
                )){
                Column (modifier = Modifier.padding(16.dp)){
                    Text(
                        text = "Expense",
                        color = FinanceTextSecondary
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "-$10,000",
                        color = FinanceExpense,
                        fontSize = 18.sp
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(28.dp))

//            Recent Transactions
            Text(
                text = "Recent Transactions",
                color = FinancePrimary,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "No transactions yet",
                color = FinanceTextSecondary,
                fontSize = 14.sp

            )
    }
}