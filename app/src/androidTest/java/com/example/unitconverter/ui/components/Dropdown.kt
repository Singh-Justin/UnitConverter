package com.example.unitconverter.ui.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class DropdownTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun dropdownOpensAndSelectsItem() {

        val items = listOf(
            DropdownItem("Item 1", "value1"),
            DropdownItem("Item 2", "value2"),
            DropdownItem("Item 3", "value3")
        )
        var selectedItem: DropdownItem? = null


        composeTestRule.setContent {
            Dropdown(text = "Select Item", items = items) { item ->
                selectedItem = item
            }
        }

        
        composeTestRule.onNodeWithText("Select Item").performClick()
        composeTestRule.onNodeWithText("Item 2").performClick()


        assert(selectedItem?.label == "Item 2")
        assert(selectedItem?.value == "value2")
    }
}
