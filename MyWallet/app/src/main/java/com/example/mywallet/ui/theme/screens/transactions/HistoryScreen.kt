
package com.example.mywallet.ui.theme.screens.transactions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywallet.ui.theme.FinanceTextSecondary
import com.example.mywallet.ui.theme.screens.addtransaction.TransactionUiModel
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.mywallet.ui.theme.FinanceExpense
import com.example.mywallet.ui.theme.FinancePrimary
import com.example.mywallet.ui.theme.FinanceSurface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun HistoryScreen(
    transactions:List<TransactionUiModel>,
    onDeleteTransaction:(Long) -> Unit,
    onEditTransaction:(Long) -> Unit
) {

    var transactionToDelete by remember { mutableStateOf<TransactionUiModel?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            text = "Transaction History",
            color = Color.White,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = ""
        )

        Spacer(modifier = Modifier.height(20.dp))

        if(transactions.isEmpty()){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "No transactions yet",
                    color = FinanceTextSecondary
                )
            }
        }else{
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items =  transactions,
                    key = {it.id}
                ){
                    transaction ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = FinanceSurface
                        ),
                        onClick = {onEditTransaction(transaction.id)}
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = transaction.category.ifBlank {
                                        transaction.type
                                    },
                                    color = if(transaction.type == "Income") FinancePrimary else FinanceExpense,
                                    fontSize = 16.sp
                                )
                                Spacer(modifier = Modifier.height(3.dp))

                                    Text(
                                        text = transaction.note.ifBlank {
                                            transaction.type
                                        } + " • "  + transaction.date,
                                        color = FinanceTextSecondary,
                                        fontSize = 12.sp
                                    )


                            }

                            val isIncome =  transaction.type == "Income"
                            val amountValue = transaction.amount.toDoubleOrNull() ?: 0.0

                            Text(
                                text = (if(isIncome) "+" else "-") + "฿" +
                                        String.format(java.util.Locale.US,"%,.2f",amountValue),
                                color = if (isIncome) {
                                    FinancePrimary
                                }  else  {
                                    FinanceExpense
                                },
                                fontSize = 16.sp
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            IconButton(
                                onClick = {
                                    transactionToDelete = transaction
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    tint = FinanceExpense
                                )
                            }

                            transactionToDelete?.let {
                                transaction ->
                                AlertDialog(
                                    onDismissRequest = {
                                        transactionToDelete = null
                                    },
                                    title =  {
                                        Text("Delete Transaction?")
                                    },
                                    text = {
                                        Text(
                                            "Are you  sure you want to delete this transaction?"
                                        )
                                    },

                                    confirmButton = {
                                        TextButton(
                                            onClick = {
                                                onDeleteTransaction(transaction.id)
                                                transactionToDelete = null
                                            }
                                        ) {
                                            Text("Delete")
                                        }
                                    },

                                    dismissButton = {
                                        TextButton(
                                            onClick = {
                                                transactionToDelete  = null
                                            }
                                        ) {
                                            Text("Cancel")
                                        }
                                    }

                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
