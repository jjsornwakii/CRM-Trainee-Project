package com.example.mywallet.ui.theme.screens.budgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Water
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywallet.ui.theme.FinanceExpense
import com.example.mywallet.ui.theme.FinancePrimary
import com.example.mywallet.ui.theme.FinanceSurface
import com.example.mywallet.ui.theme.FinanceTextPrimary
import com.example.mywallet.ui.theme.FinanceTextSecondary

@Composable
fun BudgetsScreen(){
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp).verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Budgets",
            color = FinanceTextPrimary,
            fontSize = 26.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Bills",
                color = FinanceTextSecondary,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = "Avaliable to Spend",
                fontSize = 12.sp,
                color = FinanceTextSecondary
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = FinanceSurface
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Computer,
                                contentDescription = "computer",
                            )
                            Text(
                                text = "Internet",
                                color = FinancePrimary,
                                fontSize = 18.sp
                            )
                        }
                        Box(
                            modifier = Modifier.clip(
                                RoundedCornerShape(20.dp)).background(Color.Gray)
                        ){
                            Text(
                                text = "฿0.00",
                                modifier = Modifier.padding(6.dp,1.dp),
                                fontSize = 12.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier =
                            Modifier.fillMaxWidth().
                            height(6.dp).
                            clip(RoundedCornerShape(20.dp)).background(FinancePrimary)
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Fully Spent",
                        color = FinanceTextSecondary,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = FinanceSurface
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Water,
                                contentDescription = "water",
                                tint = Color.Blue
                            )
                            Text(
                                text = "Water",
                                color = FinancePrimary,
                                fontSize = 18.sp
                            )
                        }
                        Box(
                            modifier = Modifier.clip(
                                RoundedCornerShape(20.dp)).background(Color.Gray)
                        ){
                            Text(
                                text = "฿0.00",
                                modifier = Modifier.padding(6.dp,1.dp),
                                fontSize = 12.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier =
                            Modifier.fillMaxWidth().
                            height(6.dp).
                            clip(RoundedCornerShape(20.dp)).background(FinancePrimary)
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Fully Spent",
                        color = FinanceTextSecondary,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = FinanceSurface
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Lightbulb,
                                contentDescription = "Lightbulb",
                                tint = Color.Yellow
                            )
                            Text(
                                text = "Electric",
                                color = FinancePrimary,
                                fontSize = 18.sp
                            )
                        }
                        Box(
                            modifier = Modifier.clip(
                                RoundedCornerShape(20.dp)).background(Color.Gray)
                        ){
                            Text(
                                text = "฿0.00",
                                modifier = Modifier.padding(6.dp,1.dp),
                                fontSize = 12.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier =
                            Modifier.fillMaxWidth().
                            height(6.dp).
                            clip(RoundedCornerShape(20.dp)).background(FinancePrimary)
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Fully Spent",
                        color = FinanceTextSecondary,
                        fontSize = 14.sp
                    )
                }
            }



        }
    }
}