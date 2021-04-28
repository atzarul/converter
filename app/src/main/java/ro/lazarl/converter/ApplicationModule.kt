package ro.lazarl.converter

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.threeten.bp.LocalDate
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ro.lazarl.converter.api.rates.RatesApi
import ro.lazarl.converter.api.serialization.CurrencyDeserializer
import ro.lazarl.converter.api.serialization.LocalDateDeserializer
import ro.lazarl.converter.common.api.mock.MockResponseInterceptor
import ro.lazarl.converter.common.scheduling.SchedulerProvider
import ro.lazarl.converter.common.scheduling.SchedulerProviderImpl
import ro.lazarl.converter.models.Currency
import javax.inject.Singleton

private const val API_BASE_URL = "https://revolut.duckdns.org/"

@Module
abstract class ApplicationModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @Singleton
        fun provideGson(): Gson =
            GsonBuilder()
                .registerTypeAdapter(Currency::class.java, CurrencyDeserializer())
                .registerTypeAdapter(LocalDate::class.java, LocalDateDeserializer())
                .create()

        @Provides
        @JvmStatic
        @Singleton
        fun provideRetrofit(
            gson: Gson,
            application: ConverterApplication
        ): Retrofit =
            Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(
                            MockResponseInterceptor(
                                application.assets,
                                mapOf(
                                    "${API_BASE_URL}latest?base=EUR" to "rates-eur.json",
                                    "${API_BASE_URL}latest?base=USD" to "rates-usd.json",
                                    "${API_BASE_URL}latest?base=RON" to "rates-ron.json",
                                    "${API_BASE_URL}latest?base=HUF" to "rates-huf.json"
                                )
                            )
                        )
                        .build()
                )
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        @Provides
        @JvmStatic
        @Singleton
        fun provideRatesApi(retrofit: Retrofit): RatesApi = retrofit.create(RatesApi::class.java)

        @Provides
        @JvmStatic
        @Singleton
        fun providerSchedulerProvider(): SchedulerProvider = SchedulerProviderImpl()

    }

}