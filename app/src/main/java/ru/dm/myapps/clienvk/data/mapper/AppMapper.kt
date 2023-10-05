package ru.dm.myapps.clienvk.data.mapper

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

open class AppMapper {
    protected fun convertDate(timestamp: Long): String {
        return SimpleDateFormat("d MM yyyy, hh:mm", Locale.getDefault())
            .format(Date(timestamp * 1000))
    }
}