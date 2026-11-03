package com.example.mywallet.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val FinanceDarkColorScheme = darkColorScheme(
    primary = FinancePrimary,
    onPrimary = FinanceBackground,

    secondary = FinancePrimary,
    background = FinanceBackground,
    onBackground = FinanceTextPrimary,

    surface = FinanceSurface,
    onSurface = FinanceTextPrimary,

    error = FinanceExpense,
    onError = FinanceTextPrimary,

    outline = FinanceBorder
)

@Composable
fun ExpenseTrackerTheme(
    content: @Composable () -> Unit
){
    MaterialTheme (
        colorScheme = FinanceDarkColorScheme,
        typography = Typography,
        content = content
    )
}