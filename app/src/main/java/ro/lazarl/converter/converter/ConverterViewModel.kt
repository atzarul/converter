package ro.lazarl.converter.converter

import androidx.lifecycle.ViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import ro.lazarl.converter.FragmentScope
import ro.lazarl.converter.common.formatting.Formatter
import ro.lazarl.converter.common.scheduling.SchedulerProvider
import ro.lazarl.converter.models.Amount
import ro.lazarl.converter.models.Currency
import ro.lazarl.converter.rates.FetchLatestRatesWithBaseOncePerSecondUseCase
import ro.lazarl.converter.rates.Rate
import javax.inject.Inject

private val DEFAULT_CURRENCY = Currency.EURO
private const val DEFAULT_AMOUNT: Double = 1.0

@FragmentScope
class ConverterViewModel @Inject constructor(
    private val amountFormatter: Formatter<Amount>,
    private val fetchLatestRatesWithBaseOncePerSecondUseCase: FetchLatestRatesWithBaseOncePerSecondUseCase,
    private val convertAmountToAllCurrenciesUseCase: ConvertAmountToAllCurrenciesUseCase,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    private var userInteractionEventsDisposable: Disposable? = null
    private val userInteractionEvents: PublishSubject<ConverterUserInteractionEvents> = PublishSubject.create()

    private var ratesDisposable: Disposable? = null
    private val ratesBaseCurrency: Flowable<Currency> =
        userInteractionEvents
            .ofType(ConverterUserInteractionEvents.AmountChanged::class.java)
            .map(ConverterUserInteractionEvents.AmountChanged::currency)
            .distinctUntilChanged()
            .toFlowable(BackpressureStrategy.LATEST)
            .startWith(DEFAULT_CURRENCY)
            .replay(1)
            .autoConnect(0)
    private val rates: PublishSubject<Map<Currency, Rate>> = PublishSubject.create()

    private val convertAmountToAllCurrencies =
        ObservableTransformer<Pair<Amount, Map<Currency, Rate>>, List<Amount>> { amountAndRates ->
            amountAndRates
                .map {
                    convertAmountToAllCurrenciesUseCase
                        .convertAmountToAllCurrencies(it.first, it.second)
                        .let { convertedAmounts ->
                            return@let convertedAmounts.toMutableList().apply {
                                add(0, it.first)
                            }
                        }
                }
        }

    private val viewStates: Observable<ConverterViewState> =
        Observable.combineLatest(
            userInteractionEvents
                .startWith(ConverterUserInteractionEvents.AmountChanged("1", Currency.EURO))
                .ofType(ConverterUserInteractionEvents.AmountChanged::class.java)
                .map { event ->
                    val amount = try {
                        event.amount.toDouble()
                    } catch (error: NumberFormatException) {
                        0.0
                    }
                    return@map Amount(amount, event.currency)
                }
                .startWith(Amount(DEFAULT_AMOUNT, DEFAULT_CURRENCY)),
            rates,
            BiFunction { amount: Amount, rates: Map<Currency, Rate> ->
                Pair(amount, rates)
            })
            .compose(convertAmountToAllCurrencies)
            .scan(ConverterViewState(true, emptyList())) { previous, amounts ->
                previous.copy(
                    isLoading = false,
                    amounts = amounts.map { amountToConverterCurrencyItemViewState(amounts[0].currency, it) }
                )
            }
            .distinctUntilChanged()
            .replay(1)
            .autoConnect(0)
            .observeOn(schedulerProvider.mainThread())

    override fun onCleared() {
        onStop()
        userInteractionEventsDisposable?.dispose()
        userInteractionEventsDisposable = null
    }

    fun bindUserInteractionEvents(
        userInteractionEvents: Observable<ConverterUserInteractionEvents>
    ) {
        userInteractionEventsDisposable =
            userInteractionEvents
                .observeOn(schedulerProvider.computation())
                .subscribe(this.userInteractionEvents::onNext)
    }

    fun viewStates(): Observable<ConverterViewState> = viewStates

    fun onStart() {
        ratesDisposable =
            ratesBaseCurrency
                .switchMap {
                    fetchLatestRatesWithBaseOncePerSecondUseCase
                        .getLatestRatesWithBaseOncePerSecond(it)
                }
                .subscribeOn(schedulerProvider.io())
                .subscribe(rates::onNext)
    }

    fun onStop() {
        ratesDisposable?.dispose()
        ratesDisposable = null
    }

    private fun amountToConverterCurrencyItemViewState(
        base: Currency,
        amount: Amount
    ): ConverterCurrencyItemViewState = ConverterCurrencyItemViewState(
        base == amount.currency,
        UiCurrency.fromCurrency(amount.currency),
        amountFormatter.format(amount)
    )

}