package com.bsuir.game_catalog.ui.component.game.list.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bsuir.game_catalog.ui.component.game.list.utils.SortOption

@Composable
fun FilterRow(
    companies: List<String>,
    selectedCompany: String?,
    onCompanySelected: (String?) -> Unit,
    sortOption: SortOption,
    onSortSelected: (SortOption) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CompanyFilterDropdown(companies, selectedCompany, onCompanySelected)
        SortDropdown(sortOption, onSortSelected)
    }
}