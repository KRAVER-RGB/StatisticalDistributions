package org.luffer.statisticaldistributions.utils

import kotlin.math.pow
import kotlin.math.roundToInt

object Tools {

    fun roundValue(value: Float, decimals: Int = 5): Float {
        val factor = 10.0.pow(decimals.toDouble()).toFloat()
        return (value * factor).roundToInt() / factor
    }

}