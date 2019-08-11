package ro.lazarl.converter.api.serialization

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.threeten.bp.LocalDate
import java.lang.reflect.Type

class LocalDateDeserializer : JsonDeserializer<LocalDate> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): LocalDate = LocalDate.parse(json.asString)
}