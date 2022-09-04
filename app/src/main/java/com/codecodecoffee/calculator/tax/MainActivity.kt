package com.codecodecoffee.calculator.tax

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codecodecoffee.taxcalculator.BuildConfig
import com.codecodecoffee.taxcalculator.R
import com.codecodecoffee.taxcalculator.ui.theme.BIRTaxCalculatorTheme
import com.google.firebase.crashlytics.FirebaseCrashlytics

class MainActivity : ComponentActivity() {

    private val viewModel: CalculatorViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseCrashlytics.getInstance().apply {
            this.setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG)
        }


        setContent {
            BIRTaxCalculatorTheme {
                BirCalculatorScreen() {
                    viewModel.calculate(it)
                }
            }
        }

    }
}

val taxResultSaver = run {
    val sssKey = "sss"
    val philHealthKey = "philHealth"
    val pagibigKey = "pagibig"
    val incomeTaxKey = "incomeTax"
    val netPayAfterDeductionsKey = "netPayAfterDeductions"

    mapSaver<TaxResult?>(save = {
        mapOf(
            sssKey to it?.sss,
            philHealthKey to it?.philHealth,
            pagibigKey to it?.pagibig,
            incomeTaxKey to it?.incomeTax,
            netPayAfterDeductionsKey to it?.netPayAfterDeductions
        )
    }, restore = { map ->
        if (map.values.all { it == null })
            null
        else
            TaxResult(
                map[sssKey] as Double,
                map[philHealthKey] as Double,
                map[pagibigKey] as Double,
                map[incomeTaxKey] as Double,
                map[netPayAfterDeductionsKey] as Double
            )
    })

}


@Composable
fun BirCalculatorScreen(onComputeResult: ((Double) -> TaxResult?)? = null) {

    val focusManager = LocalFocusManager.current
    val focusRequester = FocusRequester()

    var monthlyIncome by rememberSaveable { mutableStateOf("") }
    var result: TaxResult? by rememberSaveable(stateSaver = taxResultSaver) {
        mutableStateOf(
            null
        )
    }

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
    ) {

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
        ) {


            Spacer(modifier = Modifier.height(11.dp))


            Card(
                modifier = Modifier
                    .padding(top = 21.dp, bottom = 11.dp)
                    .padding(horizontal = 31.dp), elevation = 5.dp
            ) {


                Column(
                    modifier = Modifier
                        .padding(5.dp)
                        .padding(horizontal = 7.dp)
                        .padding(bottom = 7.dp), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(11.dp))


                    OutlinedTextField(modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester)
                        .padding(horizontal = 5.dp),
                        value = monthlyIncome,
                        onValueChange = {
                            monthlyIncome = it
                        },
                        textStyle = TextStyle(fontSize = 19.sp),
                        label = { Text(text = "Monthly Income") },

                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            autoCorrect = false,
                            imeAction = ImeAction.Go
                        ),
                        keyboardActions = KeyboardActions(onGo = {
                            monthlyIncome.toDoubleOrNull()?.apply {
                                result = onComputeResult?.invoke(this)
                                focusManager.clearFocus()
                            }
                        }),
                        trailingIcon = {

                            IconButton(onClick = {
                                monthlyIncome = ""
                                result = null
                                focusRequester.requestFocus()
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    modifier = Modifier.padding(horizontal = 5.dp),
                                    contentDescription = "Clear"
                                )
                            }
                        })

                    Spacer(modifier = Modifier.height(11.dp))

                    Button(onClick = {
                        monthlyIncome.toDoubleOrNull()?.apply {
                            result = onComputeResult?.invoke(this)
                            focusManager.clearFocus()
                        }

                    }) {
                        Text(
                            "Compute",
                            modifier = Modifier.padding(horizontal = 15.dp),
                            fontSize = 15.sp
                        )
                    }
                }

            }

            if (result == null) {
                BirIntro()

            } else {
                ResultContent(result!!)
            }

        }

    }
}

@Composable
private fun BirIntro() {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(51.dp))
        Icon(
            painter = painterResource(id = R.drawable.calculate),
            modifier = Modifier.size(311.dp),
            contentDescription = "",
            tint = MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
        )

        Text(
            text = buildAnnotatedString {
                append("Type your")
                withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                    append(" monthly income")

                }
                append(" and click on the button to compute your ")
                withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                    append("BIR tax.")

                }
            },
            fontSize = 17.sp,
            modifier = Modifier.padding(horizontal = 31.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
        )
    }

}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    BIRTaxCalculatorTheme {

        Surface {
            BirCalculatorScreen()
        }

    }
}