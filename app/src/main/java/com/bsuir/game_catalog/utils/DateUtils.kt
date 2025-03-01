package com.bsuir.game_catalog.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(selectedDay: Int, selectedMonth: Int, selectedYear: Int): String {
    return String.format(
        Locale.ENGLISH,
        "%02d/%02d/%d",
        selectedDay,
        selectedMonth + 1,
        selectedYear
    )
}

fun parseDate(dateStr: String): Date? {
    return try {
        SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH).parse(dateStr)
    } catch (e: Exception) {
        null
    }
}