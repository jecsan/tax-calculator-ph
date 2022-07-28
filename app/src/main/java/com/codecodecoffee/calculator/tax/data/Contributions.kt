package com.codecodecoffee.calculator.tax.data

data class SssContribution(val min: Double, val max: Double, val value: Double?)

data class IncomeTax(
    val min: Double,
    val max: Double,
    val multiplier: Double? = null,
    val fixedTax: Double? = null
)

data class PagibigContribution(
    val min: Double,
    val max: Double,
    val multiplier: Double?,
    val value: Double? = null
)

data class PhilHealthContribution(
    val min: Double,
    val max: Double,
    val multiplier: Double? = null,
    val divideBy: Double? = null,
    val value: Double? = null
)


val philHealthTable = listOf(
    PhilHealthContribution(min = 0.0, max = 10000.0, multiplier = null, value = 400.0),
    PhilHealthContribution(min = 10000.01, max = 79999.99, multiplier = 0.04),
    PhilHealthContribution(min = 80000.00, max = Double.MAX_VALUE, null, null, 3200.0),
)

val pagIbigTable = listOf(
    PagibigContribution(0.0, 1500.0, 0.01),
    PagibigContribution(1500.0, 5000.0, 0.02),
    PagibigContribution(5000.0, Double.MAX_VALUE, null, value = 100.0),
)


val sssTable = listOf(
    SssContribution(1000.0, 3249.99, 135.0),
    SssContribution(3250.0, 3749.99, 157.50),
    SssContribution(3750.0, 4249.99, 180.0),
    SssContribution(4250.0, 4749.99, 202.5),
    SssContribution(4750.0, 5249.99, 225.0),
    SssContribution(5250.0, 5749.99, 247.5),
    SssContribution(5750.0, 6249.99, 270.0),
    SssContribution(6250.0, 6749.99, 292.5),
    SssContribution(6750.0, 7249.99, 315.0),
    SssContribution(7250.0, 7749.99, 337.5),
    SssContribution(7750.0, 8249.99, 360.0),
    SssContribution(8250.0, 8749.99, 382.5),
    SssContribution(8750.0, 9249.99, 405.0),
    SssContribution(9250.0, 9749.99, 427.5),
    SssContribution(9750.0, 10249.99, 450.0),
    SssContribution(10250.0, 10749.99, 472.5),
    SssContribution(10750.0, 11249.99, 495.0),
    SssContribution(11250.0, 11749.99, 517.5),
    SssContribution(11750.0, 12249.99, 540.0),
    SssContribution(12250.0, 12749.99, 562.5),
    SssContribution(12750.0, 13249.99, 585.0),
    SssContribution(13250.0, 13749.99, 607.5),
    SssContribution(13750.0, 14249.99, 630.0),
    SssContribution(14250.0, 14749.99, 652.5),
    SssContribution(14750.0, 15249.99, 675.0),
    SssContribution(15250.0, 15749.99, 697.5),
    SssContribution(15750.0, 16249.99, 720.0),
    SssContribution(16250.0, 16749.99, 742.5),
    SssContribution(16750.0, 17249.99, 765.0),
    SssContribution(17250.0, 17749.99, 787.5),
    SssContribution(17750.0, 18249.99, 810.0),
    SssContribution(18250.0, 18749.99, 832.5),
    SssContribution(18750.0, 19249.99, 855.0),
    SssContribution(19250.0, 19749.99, 877.5),
    SssContribution(19750.0, 20249.99, 900.0),
    SssContribution(20250.0, 20749.99, 922.5),
    SssContribution(20750.0, 21249.99, 945.0),
    SssContribution(21250.0, 21749.99, 967.5),
    SssContribution(21750.0, 22249.99, 990.0),
    SssContribution(22250.0, 22749.99, 1012.5),
    SssContribution(22270.0, 23249.99, 1035.0),
    SssContribution(23250.0, 23749.99, 1057.5),
    SssContribution(23750.0, 24249.99, 1080.0),
    SssContribution(24250.0, 24279.99, 1102.5),
    SssContribution(24750.0, Double.MAX_VALUE, 1125.0),
)


val incomeTaxTable = listOf(
    IncomeTax(0.0, 20833.0, null),
    IncomeTax(20833.0, 33332.00, 0.20),
    IncomeTax(33333.0, 66666.0, 0.25, 2500.00),
    IncomeTax(66667.0, 166666.0, 0.30, 10833.33),
    IncomeTax(166667.0, 666666.0, 0.32, 40833.33),
    IncomeTax(666666.0, Double.MAX_VALUE, 0.35, 200833.33),
)