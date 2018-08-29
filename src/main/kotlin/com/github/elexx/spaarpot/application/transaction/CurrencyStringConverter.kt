package com.github.elexx.spaarpot.application.transaction

import javafx.util.StringConverter
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.ParsePosition

class CurrencyStringConverter : StringConverter<BigDecimal>() {
    private val format = DecimalFormat("#,###.00#")

    init {
        format.isParseBigDecimal = true
    }

    fun isParseable(value: String): Boolean {
        val position = ParsePosition(0)
        format.parse(value, position)
        return position.index == value.length
    }

    override fun toString(value: BigDecimal?): String {
        return if (value != null) format.format(value) else ""
    }

    override fun fromString(value: String?): BigDecimal {
        return if (value != null) format.parse(value) as BigDecimal else BigDecimal.ZERO
    }

}
