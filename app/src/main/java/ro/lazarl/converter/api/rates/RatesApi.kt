package ro.lazarl.converter.api.rates

import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Query

interface RatesApi {

    @GET("/latest")
    fun getLatestRatesWithBase(@Query("base") base: String): Maybe<GetRatesWithBaseResponse>

}