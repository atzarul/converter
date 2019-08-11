package ro.lazarl.converter.converter

data class ConverterViewState(
    val isLoading: Boolean,
    val amounts: List<ConverterCurrencyItemViewState>
)