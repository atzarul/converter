package ro.lazarl.converter.api.rates

import com.google.gson.GsonBuilder
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.threeten.bp.LocalDate
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ro.lazarl.converter.api.serialization.CurrencyDeserializer
import ro.lazarl.converter.api.serialization.LocalDateDeserializer
import ro.lazarl.converter.models.Currency
import java.io.InputStreamReader
import java.net.HttpURLConnection

private const val TEST_FILE_GET_LATEST_RATES_WITH_BASE = "getLatestRatesWithBase.json"

@RunWith(JUnit4::class)
class RatesApiTest {

    private val mockWebServer: MockWebServer = MockWebServer()

    private val gson =
        GsonBuilder()
            .registerTypeAdapter(LocalDate::class.java, LocalDateDeserializer())
            .registerTypeAdapter(Currency::class.java, CurrencyDeserializer())
            .create()

    private val api: RatesApi =
        Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(RatesApi::class.java)


    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getLatestRatesWithBase correct request`() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST) /* Unimportant, just need a response */
        )

        api.getLatestRatesWithBase(Currency.EURO.code)
            .test()

        val request = mockWebServer.takeRequest()

        assertEquals("GET", request.method)
        assertEquals("/latest?base=${Currency.EURO.code}", request.path)
    }

    @Test
    fun `getLatestRatesWithBase correct response`() {
        enqueueResponseFromFile(fileName = TEST_FILE_GET_LATEST_RATES_WITH_BASE)

        api.getLatestRatesWithBase(Currency.EURO.code)
            .test()
            .apply {
                assertNoErrors()
                assertValue(loadResponseFromResources<GetRatesWithBaseResponse>(TEST_FILE_GET_LATEST_RATES_WITH_BASE))
                assertComplete()
            }
    }

    private fun enqueueResponse(
        responseCode: Int = HttpURLConnection.HTTP_OK,
        body: String,
        headers: Map<String, String> = emptyMap()
    ): Unit =
        mockWebServer.enqueue(
            MockResponse().apply {
                setResponseCode(responseCode)
                headers.forEach { (key, value) -> addHeader(key, value) }
                setBody(body)
            }
        )

    private fun enqueueResponseFromFile(
        responseCode: Int = HttpURLConnection.HTTP_OK,
        fileName: String,
        headers: Map<String, String> = emptyMap()
    ) {
        javaClass.classLoader?.getResourceAsStream("api-response/$fileName")?.let { inputStream ->
            enqueueResponse(
                responseCode,
                inputStream.source().buffer().readString(Charsets.UTF_8),
                headers
            )
        }
    }

    private inline fun <reified R> loadResponseFromResources(fileName: String): R? =
        javaClass.classLoader?.getResourceAsStream("api-response/$fileName")?.let { inputStream ->
            val reader = InputStreamReader(inputStream)
            val result = gson.fromJson<R>(reader, R::class.java)
            reader.close()
            inputStream.close()
            result
        }

}