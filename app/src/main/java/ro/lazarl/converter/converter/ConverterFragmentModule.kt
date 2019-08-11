package ro.lazarl.converter.converter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.Binds
import dagger.Module
import dagger.Provides
import ro.lazarl.converter.FragmentScope
import ro.lazarl.converter.common.formatting.AmountFormatter
import ro.lazarl.converter.common.formatting.Formatter
import ro.lazarl.converter.common.scheduling.SchedulerProvider
import ro.lazarl.converter.models.Amount
import ro.lazarl.converter.rates.FetchLatestRatesWithBaseOncePerSecondUseCase

@Module
abstract class ConverterFragmentModule {

    @Binds
    @FragmentScope
    abstract fun provideAmountFormatter(amountFormatter: AmountFormatter): Formatter<Amount>

    @Module
    companion object {

        @Provides
        @JvmStatic
        @FragmentScope
        fun provideViewModel(
            fragment: ConverterFragment,
            amountFormatter: @JvmSuppressWildcards Formatter<Amount>,
            fetchLatestRatesWithBaseOncePerSecondUseCase: FetchLatestRatesWithBaseOncePerSecondUseCase,
            convertAmountToAllCurrenciesUseCase: ConvertAmountToAllCurrenciesUseCase,
            schedulerProvider: SchedulerProvider
        ): ConverterViewModel =
            ViewModelProviders
                .of(fragment, object : ViewModelProvider.Factory {
                    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                        ConverterViewModel(
                            amountFormatter,
                            fetchLatestRatesWithBaseOncePerSecondUseCase,
                            convertAmountToAllCurrenciesUseCase,
                            schedulerProvider
                        ) as T
                })
                .get(ConverterViewModel::class.java)

    }

}