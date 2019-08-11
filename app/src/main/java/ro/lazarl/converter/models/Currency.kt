package ro.lazarl.converter.models

enum class Currency(val code: String) {

    AUSTRALIAN_DOLLAR("AUD"),
    LEVA("BGN"),
    BRAZILIAN_REAL("BRL"),
    CANADIAN_DOLLAR("CAD"),
    SWISS_FRANC("CHF"),
    CHINESE_YUAN("CNY"),
    CZECH_KORUNA("CZK"),
    DANISH_KRONE("DKK"),
    EURO("EUR"),
    POUND_STERLING("GBP"),
    HONG_KONG_DOLLAR("HKD"),
    CROATIAN_KUNA("HRK"),
    HUNGARIAN_FORINT("HUF"),
    INDONESIAN_RUPIAH("IDR"),
    ISRAELI_NEW_SHEKEL("ILS"),
    INDIAN_RUPEE("INR"),
    ICELANDIC_KRONA("ISK"),
    JAPANESE_YEN("JPY"),
    SOUTH_KOREAN_WON("KRW"),
    MEXICAN_PESO("MXN"),
    MALAYSIAN_RINGGIT("MYR"),
    NORWEGIAN_KRONE("NOK"),
    NEW_ZEALAND_DOLLAR("NZD"),
    PHILIPPINE_PESO("PHP"),
    POLISH_ZLOTY("PLN"),
    ROMANIAN_LEU("RON"),
    RUSSIAN_RUBLE("RUB"),
    SWEDISH_KRONA("SEK"),
    SINGAPORE_DOLLAR("SGD"),
    THAI_BAHT("THB"),
    TURKISH_LIRA("TRY"),
    UNITED_STATES_DOLLAR("USD"),
    SOUTH_AFRICAN_RAND("ZAR"),
    UNKNOWN("UNKNOWN");

    companion object {

        private val codeToCurrencyMapping: Map<String, Currency> by lazy(LazyThreadSafetyMode.NONE) {
            mutableMapOf<String, Currency>().apply {
                values()
                    .filter { UNKNOWN != it }
                    .forEach { this[it.code] = it }
            }
        }

        fun fromCode(value: String) = codeToCurrencyMapping[value] ?: UNKNOWN

    }

}