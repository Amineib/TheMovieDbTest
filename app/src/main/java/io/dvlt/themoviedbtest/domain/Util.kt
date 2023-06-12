package io.dvlt.themoviedbtest.domain

import java.text.DecimalFormat

fun Float.truncate(): Float {
    val decimalFormat = DecimalFormat("#.##")
    return decimalFormat.format(this).toFloat()
}
