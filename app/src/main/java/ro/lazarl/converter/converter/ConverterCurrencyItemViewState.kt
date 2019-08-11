package ro.lazarl.converter.converter

data class ConverterCurrencyItemViewState(
    val isBase: Boolean,
    val uiCurrency: UiCurrency,
    val amount: String
)