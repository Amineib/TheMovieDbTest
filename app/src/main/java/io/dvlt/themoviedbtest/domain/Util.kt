package io.dvlt.themoviedbtest.domain

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun Float.truncate(): Float {
    val decimalFormat = DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.getDefault()))
    return decimalFormat.format(this).toFloat()
}
