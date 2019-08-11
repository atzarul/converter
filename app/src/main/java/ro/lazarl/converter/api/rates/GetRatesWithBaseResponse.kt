package ro.lazarl.converter.api.rates

import org.threeten.bp.LocalDate
import ro.lazarl.converter.models.Currency

data class GetRatesWithBaseResponse(
    val base: Currency,
    val date: LocalDate,
    val rates: Map<Currency, Double>
)