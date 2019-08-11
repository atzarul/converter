package ro.lazarl.converter.api.serialization

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import ro.lazarl.converter.models.Currency

private const val KEY_CURRENCY = "currency"

@RunWith(JUnit4::class)
class CurrencyDeserializerTest {

    private val gson =
        GsonBuilder()
            .registerTypeAdapter(Currency::class.java, CurrencyDeserializer())
            .create()

    @Test
    fun `deserialize valid currency code yields expected currency`() {
        Currency.values()
            .filter { Currency.UNKNOWN != it }
            .map(::HasCurrency)
            .forEach { expected ->
                assertEquals(
                    expected,
                    gson.fromJson(expected.toJson(), HasCurrency::class.java)
                )
            }
    }

    @Test
    fun `deserialize invalid currency code yields unknown currency`() {
        HasCurrency(Currency.UNKNOWN).let { expected ->
            assertEquals(
                expected,
                gson.fromJson(
                    expected.toJson().replace(Currency.UNKNOWN.code, "AAA"),
                    HasCurrency::class.java
                )
            )
        }
    }

    private data class HasCurrency(
        @SerializedName(KEY_CURRENCY) val currency: Currency
    ) {
        fun toJson(): String = "{ \"$KEY_CURRENCY\": \"${currency.code}\" }"
    }

}