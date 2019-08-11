package ro.lazarl.converter.common.formatting

import ro.lazarl.converter.models.Amount
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import javax.inject.Inject

class AmountFormatter @Inject constructor() : Formatter<Amount> {

    override fun format(
        value: Amount
    ): String {
        return DecimalFormat("0.##", DecimalFormatSymbols.getInstance(Locale.getDefault())).format(value.amount)
    }

}