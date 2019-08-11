package ro.lazarl.converter.api.serialization

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import ro.lazarl.converter.models.Currency
import java.lang.reflect.Type

class CurrencyDeserializer : JsonDeserializer<Currency> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Currency = Currency.fromCode(json.asString)

}