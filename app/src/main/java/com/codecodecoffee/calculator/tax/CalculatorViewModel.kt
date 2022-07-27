package com.codecodecoffee.calculator.tax

import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {



    fun calculate(monthlySalary: Double): TaxResult {
        return TaxCalculator.calculate(monthlySalary)
    }


}