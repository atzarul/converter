package ro.lazarl.converter.rates

import io.reactivex.Flowable
import ro.lazarl.converter.api.rates.RatesApi
import ro.lazarl.converter.models.Currency
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FetchLatestRatesWithBaseOncePerSecondUseCase @Inject constructor(
    private val api: RatesApi
) {

    fun getLatestRatesWithBaseOncePerSecond(
        base: Currency
    ): Flowable<Map<Currency, Rate>> =
        Flowable.interval(0L, 1L, TimeUnit.SECONDS)
            .flatMapMaybe {
                api.getLatestRatesWithBase(base.code)
                    .onErrorComplete()
            }
            .map { response ->
                return@map mutableMapOf<Currency, Rate>().also { rates ->
                    rates[response.base] = Rate(base, base, response.date, 1.0)
                    response.rates
                        .map { Rate(it.key, response.base, response.date, it.value) }
                        .forEach { rates[it.currency] = it }

                }
            }

}