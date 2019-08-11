package ro.lazarl.converter.converter

import ro.lazarl.converter.models.Amount
import ro.lazarl.converter.models.Currency
import ro.lazarl.converter.rates.Rate
import javax.inject.Inject

class ConvertAmountToAllCurrenciesUseCase @Inject constructor() {

    fun convertAmountToAllCurrencies(
        amount: Amount,
        rates: Map<Currency, Rate>
    ): List<Amount> =
        Currency.values()
            .filter { amount.currency != it && Currency.UNKNOWN != it }
            .mapNotNull(rates::get)
            .map { rate -> Amount(amount.amount * rate.rate, rate.currency) }

}