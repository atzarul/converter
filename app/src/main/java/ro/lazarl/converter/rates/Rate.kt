package ro.lazarl.converter.rates

import org.threeten.bp.LocalDate
import ro.lazarl.converter.models.Currency

data class Rate(
    val currency: Currency,
    val base: Currency,
    val date: LocalDate,
    val rate: Double
)