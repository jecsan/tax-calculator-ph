package com.codecodecoffee.calculator.tax

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.*


@Composable
fun ResultContent(result: TaxResult) {

    val numberFormatter = NumberFormat.getCurrencyInstance(Locale("en", "PH"))

    Text(
        text = "Contributions ",
        modifier = Modifier
            .padding(horizontal = 31.dp)
            .padding(bottom = 11.dp),
        fontWeight = FontWeight.SemiBold
    )

    Card(
        modifier = Modifier.padding(horizontal = 31.dp), elevation = 5.dp
    ) {


        Column(
            modifier = Modifier.padding(17.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = numberFormatter.format(result.sss),
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "SSS") },
            )
            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = numberFormatter.format(result.philHealth),
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Philhealth   ") },
            )
            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusable(false),
                value = numberFormatter.format(result.pagibig),
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Pag-IBIG") },
            )


        }

    }


    Text(
        text = "Computation Result",
        modifier = Modifier
            .padding(horizontal = 31.dp)
            .padding(vertical = 11.dp),
        fontWeight = FontWeight.SemiBold
    )



    Card(
        modifier = Modifier
            .padding(horizontal = 31.dp)
            .padding(top = 11.dp),
        elevation = 5.dp,
    ) {


        Column(
            modifier = Modifier.padding(17.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = numberFormatter.format(result.incomeTax),
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Income Tax") },
            )
            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = numberFormatter.format(result.sss + result.philHealth + result.pagibig),
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Total Contributions") },
            )
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusable(false),
                value = numberFormatter.format(result.netPayAfterDeductions),
                onValueChange = {},
                textStyle = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 19.sp,
                    color = MaterialTheme.colors.primaryVariant
                ),
                readOnly = true,
                label = {
                    Text(
                        text = "Net Pay after Deductions",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                    )
                },
            )


        }

    }
}