package ro.lazarl.converter.converter

import ro.lazarl.converter.models.Currency

sealed class ConverterUserInteractionEvents {
    data class AmountChanged(
        val amount: String,
        val currency: Currency
    ) : ConverterUserInteractionEvents()
}