package ro.lazarl.converter.api.serialization

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

private const val KEY_DATE = "date"

@RunWith(JUnit4::class)
class LocalDateDeserializerTest {

    private val gson: Gson =
        GsonBuilder()
            .registerTypeAdapter(LocalDate::class.java, LocalDateDeserializer())
            .create()

    @Test
    fun `deserialize date from YYYY-MM-DD string`() {
        HasLocalDate(LocalDate.of(2019, 2, 28)).also { expected ->
            assertEquals(
                expected,
                gson.fromJson(expected.toJson(), HasLocalDate::class.java)
            )
        }

    }

    private data class HasLocalDate(
        @SerializedName(KEY_DATE) val date: LocalDate
    ) {
        fun toJson(): String =
            "{ \"$KEY_DATE\": \"${DateTimeFormatter.ISO_LOCAL_DATE.format(date)}\" }"
    }

}