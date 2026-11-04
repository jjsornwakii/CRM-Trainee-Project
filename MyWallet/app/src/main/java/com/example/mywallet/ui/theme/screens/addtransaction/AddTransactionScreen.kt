package com.example.mywallet.ui.theme.screens.addtransaction

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mywallet.ui.theme.FinanceBackground
import com.example.mywallet.ui.theme.FinanceBorder
import com.example.mywallet.ui.theme.FinanceExpense
import com.example.mywallet.ui.theme.FinancePrimary
import com.example.mywallet.ui.theme.FinanceSurface
import com.example.mywallet.ui.theme.FinanceTextPrimary
import com.example.mywallet.ui.theme.FinanceTextSecondary
import com.example.mywallet.viewmodel.TransactionViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    onSave:(TransactionUiModel) ->  Unit,
    onCancel:() ->  Unit,
    viewModel: TransactionViewModel = viewModel(),
    initailTransaction: TransactionUiModel? =  null
){
    LaunchedEffect(initailTransaction?.id) {
        if(initailTransaction != null){
            viewModel.loadTransaction(initailTransaction)
        }else{
            viewModel.onResetStatevalue()
        }
    }
    val uiState = viewModel.uiState


//    FormState
    Column(
        modifier = Modifier.fillMaxSize().background(FinanceBackground)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Cancel",
                color = FinanceTextSecondary,
                modifier = Modifier.clickable{
                    onCancel()
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = if(initailTransaction == null) "Add Transaction" else "Edit Transaction",
                color = FinancePrimary,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.weight(1f))

        }

//        Spacer to  balance header
        Spacer(modifier = Modifier.height(20.dp))

//        Transaction Type
        Text(
            text = "Transaction Type",
            color =  FinanceTextSecondary
        )


        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            FilterChip(
                selected = uiState.type == "Income",
                onClick = {
                    viewModel.updateType("Income")
                },
                label = { Text("Income") },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = FinancePrimary,
                    selectedLabelColor = FinanceBackground,
                    labelColor = FinanceTextSecondary
                ),
                modifier = Modifier.weight(1f)
            )

            FilterChip(
                selected = uiState.type == "Expense",
                onClick = {
                    viewModel.updateType("Expense")
                },
                label = { Text("Expense") },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = FinancePrimary,
                    selectedLabelColor = FinanceBackground,
                    labelColor = FinanceTextSecondary
                ),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

//            Amount
            Text(
                text = "Amount",
                color = FinanceTextSecondary
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = uiState.amount,
                onValueChange = {
                    viewModel.updateAmount(it)
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("0.00")
                },
                leadingIcon = {
                    Text(
                        text = "฿",
                        color = if(uiState.type ==  "Income") FinancePrimary else FinanceExpense,
                        fontSize = 20.sp
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = FinanceTextPrimary,
                    unfocusedTextColor = FinanceTextPrimary,
                    focusedBorderColor = FinancePrimary,
                    unfocusedBorderColor = FinanceBorder,
                    cursorColor = FinancePrimary,
                    focusedPlaceholderColor = FinanceTextSecondary,
                    unfocusedPlaceholderColor = FinanceTextSecondary
                )
            )

        if((uiState.amount.toDoubleOrNull() ?: 0.0) < 1.0){
            Spacer(Modifier.height(3.dp))

            Text(
                text =  "Amount Can Not Less Than  1",
                color = FinanceExpense,
                fontSize = 12.sp
            )
        }

            Spacer(modifier = Modifier.height(24.dp))


//            Category Dropdown
            Text(
                text = "Category",
                color = FinanceTextSecondary
            )

            Spacer(modifier = Modifier.height(8.dp))

            ExposedDropdownMenuBox(
                expanded = uiState.expanded,
                onExpandedChange = {
                    viewModel.updateExpanded(uiState.expanded)
                }
            ) {
                OutlinedTextField(
                    value =
                        if (uiState.type == "Income" &&  uiState.category.isNullOrEmpty())
                            "Salary"
                        else if (uiState.type ==  "Expense" && uiState.category.isNullOrBlank())
                            "Food"
                        else uiState.category,
                    onValueChange = {
                    },
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth()
                        .menuAnchor(
                            MenuAnchorType.PrimaryEditable
                        ),
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = uiState.expanded
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = FinanceTextPrimary,
                        unfocusedTextColor = FinanceTextPrimary,
                        focusedBorderColor = FinancePrimary,
                        unfocusedBorderColor = FinanceBorder
                    )
                )

                ExposedDropdownMenu(
                    expanded = uiState.expanded,
                    onDismissRequest = {
                        viewModel.updateExpanded(uiState.expanded)
                    },
                    modifier = Modifier.background(FinanceSurface)
                ) {
                    viewModel.categories.forEach {item ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = item,
                                    color = FinancePrimary
                                )
                            },
                            onClick = {
                                viewModel.updateCategory(item)
                                viewModel.updateExpanded(uiState.expanded)
                            }
                        )
                    }
                }
            }
        if(uiState.category.isNullOrBlank()){
            Spacer(Modifier.height(3.dp))
            Text(
                text =  "Please select Category",
                color = FinanceExpense,
                fontSize = 12.sp
            )
        }


            Spacer(modifier = Modifier.height(24.dp))

//            Date Picker
            Text(
                text = "Date",
                color = FinanceTextSecondary
            )

            Spacer(Modifier.height(8.dp))

            OutlinedButton(
                onClick = {
                    viewModel.updateShowDatePicker(uiState.showDatePicker)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = FinancePrimary
                )
            ) {
                Text(
                    uiState.date
                )
            }

            if(uiState.showDatePicker){
                val  datePrickerState = rememberDatePickerState()
                DatePickerDialog(
                    onDismissRequest = {
                        viewModel.updateShowDatePicker(uiState.showDatePicker)
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                datePrickerState.selectedDateMillis?.let{
                                    viewModel.updateDate(
                                        SimpleDateFormat("dd/MM/yyyy",
                                            Locale.getDefault()).format(Date(it))
                                    )
                                }
//                                close datepicker
                                viewModel.updateShowDatePicker(uiState.showDatePicker)

                            }
                        ) { Text("ok",color = FinancePrimary)}
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
//                                close datepicker
                                viewModel.updateShowDatePicker(uiState.showDatePicker)
                            }
                        ) {
                            Text("Cancel")
                        }
                    }
                ) {
                    DatePicker(state = datePrickerState)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

//            Note
            Text(
                text = "Descrpition",
                color  = FinanceTextSecondary
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = uiState.note,
                onValueChange = {
                    viewModel.updateNote(it)
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("What was this transaction for?")
                },
                minLines = 2,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = FinanceTextPrimary,
                    unfocusedTextColor = FinanceTextPrimary,
                    focusedBorderColor = FinancePrimary,
                    unfocusedBorderColor = FinanceBorder,
                    unfocusedPlaceholderColor = FinanceTextSecondary
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

//            Save Button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {
                    val  transaction = TransactionUiModel(
                        id =  initailTransaction?.id ?: 0L,
                        type = uiState.type,
                        amount = uiState.amount,
                        category = uiState.category,
                        note = uiState.note,
                        date = uiState.date
                    )
                    onSave(transaction)
                },
                enabled = viewModel.validAmount,
                modifier = Modifier.height(40.dp).weight(3f),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = FinancePrimary,
                    contentColor = FinanceBackground,
                    disabledContainerColor = FinanceBorder
                )
            ) {
                Text(
                    text = if(initailTransaction == null) "Save Transaction" else "Update Transaction", fontSize = 16.sp,
                )
            }

            Button(
                onClick = {
                    viewModel.onResetStatevalue()
                },
                modifier = Modifier.weight(1f).height(40.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = FinanceSurface,
                    contentColor = FinanceTextSecondary
                )
            ) {
                Text(
                    text = "Clear"
                )
            }
        }

            Spacer(modifier = Modifier.height(20.dp))

    }
}