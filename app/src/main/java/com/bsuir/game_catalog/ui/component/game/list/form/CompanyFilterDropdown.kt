package com.bsuir.game_catalog.ui.component.game.list.form

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bsuir.game_catalog.R
import com.bsuir.game_catalog.ui.component.general.ClickableOutlinedTextField

@Composable
fun RowScope.CompanyFilterDropdown(
    companies: List<String>,
    selectedCompany: String?,
    onCompanySelected: (String?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier.weight(1.2f))  {
        ClickableOutlinedTextField(
            value = selectedCompany ?: stringResource(R.string.all_company),
            onClick = { expanded = true },
            label = stringResource(R.string.company),
            trailingIcon = {
                Icon(
                    imageVector =
                        if (expanded) Icons.Default.KeyboardArrowUp
                        else Icons.Default.KeyboardArrowDown,
                    contentDescription = stringResource(R.string.dropdown)
                )
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .heightIn(max = 200.dp)
        ) {
            DropdownMenuItem(
                text = { Text(stringResource(R.string.all_company)) },
                onClick = { onCompanySelected(null); expanded = false }
            )
            companies.forEach { company ->
                DropdownMenuItem(
                    text = { Text(company) },
                    onClick = { onCompanySelected(company); expanded = false }
                )
            }
        }
    }
}