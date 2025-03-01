package com.bsuir.game_catalog.ui.component.date

import android.app.DatePickerDialog
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.bsuir.game_catalog.R
import com.bsuir.game_catalog.ui.component.general.ClickableOutlinedTextField
import com.bsuir.game_catalog.utils.formatDate
import java.util.Calendar

@Composable
fun DatePickerField(
    label: String,
    date: String,
    onDateSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val datePickerDialog = DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            val formattedDate = formatDate(selectedDay, selectedMonth, selectedYear)
            onDateSelected(formattedDate)
        },
        Calendar.getInstance().get(Calendar.YEAR),
        Calendar.getInstance().get(Calendar.MONTH),
        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    )

    ClickableOutlinedTextField(
        value = date,
        onClick = datePickerDialog::show,
        label = label,
        trailingIcon = {
            Icon(Icons.Default.DateRange, contentDescription = stringResource(R.string.select_date))
        }
    )
}
