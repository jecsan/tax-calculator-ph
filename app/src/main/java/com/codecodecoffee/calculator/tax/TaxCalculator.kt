package com.codecodecoffee.calculator.tax

import com.codecodecoffee.calculator.tax.data.incomeTaxTable
import com.codecodecoffee.calculator.tax.data.pagIbigTable
import com.codecodecoffee.calculator.tax.data.philHealthTable
import com.codecodecoffee.calculator.tax.data.sssTable

object TaxCalculator {
    private fun getSssContribution(salary: Double): Double {
        var sssValue = 0.0
        for (sssContribution in sssTable) {
            if (salary in sssContribution.min..sssContribution.max) {
                sssValue = sssContribution.value ?: 0.0
                break
            }

        }
        return sssValue
    }

    private fun getPhilHealthContribution(salary: Double): Double {
        var value = 0.0

        for (philHealthContribution in philHealthTable) {
            if (salary in philHealthContribution.min..philHealthContribution.max) {
                value =  philHealthContribution.value
                    ?: ((salary * philHealthContribution.multiplier!!) / philHealthContribution.divideBy!!)
                break
            }
        }

        return  value
    }

    private fun getPagIBIGContribution(salary: Double): Double {
        var value = 0.0

        for (pagIbigContribution in pagIbigTable) {
            if (salary in pagIbigContribution.min..pagIbigContribution.max) {
                value =  pagIbigContribution.value ?: (salary * pagIbigContribution.multiplier!!)
                break
            }
        }
        return value
    }

    private fun getTaxContribution(taxableIncome: Double): Number {

        var tax = 0.0

        for (income in incomeTaxTable) {
            if (taxableIncome in income.min..income.max) {
                tax = if (income.multiplier != null) {
                    if (income.fixedTax != null) {
                        ((taxableIncome - income.min) * income.multiplier) + income.fixedTax
                    } else {
                        (( taxableIncome - income.min) * income.multiplier)
                    }

                } else {
                    income.min
                }
                break

            }
        }

        return tax
    }


    fun calculate(monthlySalary: Double): TaxResult {

        val sssContribution = getSssContribution(monthlySalary)
        val pagibigContribution = getPagIBIGContribution(monthlySalary)
        val philhealthContribution = getPhilHealthContribution(monthlySalary)
        val totalContributions = sssContribution + pagibigContribution + philhealthContribution
        val taxableIncome = monthlySalary - totalContributions

        val incomeTax = getTaxContribution(taxableIncome).toDouble()

        return TaxResult(
            sssContribution,
            philhealthContribution,
            pagibigContribution,
            incomeTax,
            monthlySalary - incomeTax - totalContributions
        )
    }
}