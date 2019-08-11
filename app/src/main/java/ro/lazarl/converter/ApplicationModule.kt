package ro.lazarl.converter

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import org.threeten.bp.LocalDate
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ro.lazarl.converter.api.rates.RatesApi
import ro.lazarl.converter.api.serialization.CurrencyDeserializer
import ro.lazarl.converter.api.serialization.LocalDateDeserializer
import ro.lazarl.converter.common.scheduling.SchedulerProvider
import ro.lazarl.converter.common.scheduling.SchedulerProviderImpl
import ro.lazarl.converter.models.Currency
import javax.inject.Singleton

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
        fun provideRetrofit(gson: Gson): Retrofit =
            Retrofit.Builder()
                .baseUrl("https://revolut.duckdns.org/")
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