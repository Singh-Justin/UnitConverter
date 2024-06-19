package com.example.unitconverter.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

data class DropdownItem(val label: String, val value: String)

@Composable
fun Dropdown(text: String, items: List<DropdownItem>, onSelect: (item: DropdownItem) -> Unit) {
    Box() {

        var isExpanded by remember {
            mutableStateOf(false)
        }

        val handleOpen: () -> Unit = {
            isExpanded = true
        }

        val handleClose: () -> Unit = {
            isExpanded = false
        }

        val handleSelect: (item: DropdownItem) -> Unit = { item ->
            onSelect(item);
            handleClose()
        }

        Button(onClick = handleOpen) {
            Text(text)
            Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Arrow")
        }

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = handleClose,

            ) {
            
        }
    }
}