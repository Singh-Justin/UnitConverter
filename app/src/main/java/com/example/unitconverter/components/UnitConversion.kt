package com.example.unitconverter.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.components.Dropdown
import com.example.unitconverter.ui.components.DropdownItem
import kotlin.math.roundToInt

val dropdownConversionItems = listOf(
    DropdownItem(label = "Centimeters", value = "0.01"),
    DropdownItem(label = "Meters", value = "1.0"),
    DropdownItem(label = "Feet", value = "0.3048"),
    DropdownItem(label = "Millimeters", value = "0.001")
)

fun convertUnits(value: Double?, inputConversion: Double?, outputConversion: Double?): Double {
    if (value == null || inputConversion == null || outputConversion == null) return 0.0

    return (value * inputConversion * 100.0 / outputConversion).roundToInt() / 100.0
}

@Composable
fun UnitConversion() {
    var textFieldValue by remember {
        mutableStateOf("")
    }

    var inputConversionItem by remember {
        mutableStateOf(dropdownConversionItems[0])
    }

    var outputConversionItem by remember {
        mutableStateOf(dropdownConversionItems[1])
    }

    val handleInputChange: (value: String) -> Unit = {
        textFieldValue = it
    }


    val handleInputConversionSelect: (item: DropdownItem) -> Unit = { item ->
        inputConversionItem = item
    }

    val handleOutputConversionSelect: (item: DropdownItem) -> Unit = { item ->
        outputConversionItem = item
    }

    fun getConversion(): String {
        return convertUnits(
            textFieldValue.toDoubleOrNull(),
            inputConversionItem.value.toDoubleOrNull(),
            outputConversionItem.value.toDoubleOrNull()
        ).toString()
    }

    val conversion = getConversion()


    Text(text = "Unit Converter", style = MaterialTheme.typography.headlineLarge)
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(value = textFieldValue, onValueChange = handleInputChange, label = {
        Text(
            text = "Enter Value"
        )
    })
    Spacer(modifier = Modifier.height(8.dp))
    Row() {


        Dropdown(
            text = inputConversionItem.label,
            items = dropdownConversionItems,
            onSelect = handleInputConversionSelect
        )


        Spacer(modifier = Modifier.width(8.dp))

        Dropdown(
            text = outputConversionItem.label,
            items = dropdownConversionItems,
            onSelect = handleOutputConversionSelect
        )

    }

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "Result: $conversion ${outputConversionItem.label}",
        style = MaterialTheme.typography.headlineLarge
    )

}