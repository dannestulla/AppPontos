package br.gohan.apppontos.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DropdownMenu(
    items: List<String>,
    onItemSelect: (String) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(items[0]) }

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .size(120.dp, 30.dp)
            .clip(RoundedCornerShape(2.dp))
            .clickable { expanded.value = !expanded.value },
    ) {
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = selectedOptionText,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.width(8.dp))
        Icon(
            Icons.Filled.ArrowDropDown, "contentDescription",
            Modifier.align(Alignment.CenterEnd)
        )
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            items.forEach { selectionOption ->
                DropdownMenuItem(
                    { Text(selectionOption, fontSize = 16.sp) },
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded.value = false
                        onItemSelect(selectionOption)
                    },
                )
            }
        }
    }
}