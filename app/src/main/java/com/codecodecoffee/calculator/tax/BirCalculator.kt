package com.codecodecoffee.calculator.tax

object BirCalculator {

    private val sssTable = arrayListOf<List<Number>>().apply {
        add(listOf(1000, 3249.99, 135))
        add(listOf(3250, 3749.99, 157.50))
        add(listOf(3750, 4249.99, 180))
        add(listOf(4250, 4749.99, 202.5))
        add(listOf(4750, 5249.99, 225))
        add(listOf(5250, 5749.99, 247.5))
        add(listOf(5750, 6249.99, 270))
        add(listOf(6250, 6749.99, 292.5))
        add(listOf(6750, 7249.99, 315))
        add(listOf(7250, 7749.99, 337.5))
        add(listOf(7750, 8249.99, 360))
        add(listOf(8250, 8749.99, 382.5))
        add(listOf(8750, 9249.99, 405))
        add(listOf(9250, 9749.99, 427.5))
        add(listOf(9750, 10249.99, 450))
        add(listOf(10250, 10749.99, 472.5))
        add(listOf(10750, 11249.99, 495))
        add(listOf(11250, 11749.99, 517.5))
        add(listOf(11750, 12249.99, 540))
        add(listOf(12250, 12749.99, 562.5))
        add(listOf(12750, 13249.99, 585))
        add(listOf(13250, 13749.99, 607.5))
        add(listOf(13750, 14249.99, 630))
        add(listOf(14250, 14749.99, 652.5))
        add(listOf(14750, 15249.99, 675))
        add(listOf(15250, 15749.99, 697.5))
        add(listOf(15750, 16249.99, 720))
        add(listOf(16250, 16749.99, 742.5))
        add(listOf(16750, 17249.99, 765))
        add(listOf(17250, 17749.99, 787.5))
        add(listOf(17750, 18249.99, 810))
        add(listOf(18250, 18749.99, 832.5))
        add(listOf(18750, 19249.99, 855))
        add(listOf(19250, 19749.99, 877.5))
        add(listOf(19750, 20249.99, 900))
        add(listOf(20250, 20749.99, 922.5))
        add(listOf(20750, 21249.99, 945))
        add(listOf(21250, 21749.99, 967.5))
        add(listOf(21750, 22249.99, 990))
        add(listOf(22250, 22749.99, 1012.5))
        add(listOf(22270, 23249.99, 1035))
        add(listOf(23250, 23749.99, 1057.5))
        add(listOf(23750, 24249.99, 1080))
        add(listOf(24250, 24279.99, 1102.5))
        add(listOf(24750, 1125))
    }

    private fun getSssContribution(salary: Double): Double {
        var sssValue = 0.0
        for (i in 0 until sssTable.size) {

            if (i == sssTable.size - 1) {
                if (salary >= sssTable[i][0].toDouble()) {
                    sssValue = 1125.0
                    break
                }
            }
            if (salary >= sssTable[i][0].toDouble() && salary <= sssTable[i][1].toDouble()) {
                sssValue = sssTable[i][2].toDouble()
                break
            }
        }

        return sssValue
    }

    private fun getPhilHealthContribution(salary: Double): Double {

        return if (salary <= 10000) {
            300.0
        } else if (salary in 10000.01..59999.99) {
            ((salary * 0.03) / 2)
//        } else if (salary >= 60000){
        } else {
            1800.0
        }
    }

    private fun getPagIBIGContribution(salary: Double): Double {
        return if (salary <= 1500) {
            salary * 0.01
        } else if (salary > 1500 && salary < 5000) {
            salary * 0.02
//        } else if (salary >= 5000){
        } else {
            100.0
        }
    }

    private fun getTaxContribution(taxableIncome: Double): Number {

        return if (taxableIncome <= 20833) {
            0.0
        } else if (taxableIncome in 20833.0..33332.0) {
            if (taxableIncome > 20833.0) {
                (taxableIncome - 20833.0) * 0.20
            } else 0.0

        } else if (taxableIncome in 33333.0..66666.0) {
            if (taxableIncome > 33333.0) {
                ((taxableIncome - 33333.0) * 0.25) + 2500.0
            } else 2500.0

        } else if (taxableIncome in 66667.0..166666.0) {

            if (taxableIncome > 66667.0) {
                (((taxableIncome - 66667.0) * 0.30) + 10833.33)
            } else 10833.33

        } else if (taxableIncome in 166667.0..666666.0) {

            if (taxableIncome > 166667.0) {
                ((taxableIncome - 166667.0) * 0.32) + 40833.33

            } else 40833.33


        } else {
            if (taxableIncome > 666667.0) {
                (((taxableIncome - 666667.0) * 0.35) + 200833.33)
            } else 200833.33

        }

    }


    fun calculate(monthlySalary: Double): BirResult {

        val sssContribution = getSssContribution(monthlySalary)
        val pagibigContribution = getPagIBIGContribution(monthlySalary)
        val philhealthContribution = getPhilHealthContribution(monthlySalary)
        val totalContributions = sssContribution + pagibigContribution + philhealthContribution
        val taxableIncome = monthlySalary - totalContributions

        val incomeTax = getTaxContribution(taxableIncome).toDouble()

        return BirResult(
            sssContribution,
            philhealthContribution,
            pagibigContribution,
            incomeTax,
            monthlySalary-incomeTax-totalContributions
        )
    }
}