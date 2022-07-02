package com.goncharov.evgeny.obstacleavoid.util

import java.text.SimpleDateFormat
import java.util.*

object FormatUtils {

    val calendar: Calendar = Calendar.getInstance()

    @Suppress("SimpleDateFormat")
    val dateFormat = SimpleDateFormat("dd MMM yyyy HH:mm:ss")
}