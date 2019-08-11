package ro.lazarl.converter.converter

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ro.lazarl.converter.R
import ro.lazarl.converter.models.Currency

enum class UiCurrency(
    val currency: Currency,
    @StringRes val codeResId: Int,
    @StringRes val nameResId: Int,
    @DrawableRes val iconResId: Int
) {

    AUSTRALIAN_DOLLAR(
        Currency.AUSTRALIAN_DOLLAR,
        R.string.currency_australian_dollar_code,
        R.string.currency_australian_dollar_name,
        R.drawable.flag_au
    ),

    LEVA(
        Currency.LEVA,
        R.string.currency_leva_code,
        R.string.currency_leva_name,
        R.drawable.flag_bg
    ),

    BRAZILIAN_REAL(
        Currency.BRAZILIAN_REAL,
        R.string.currency_brazilian_real_code,
        R.string.currency_brazilian_real_name,
        R.drawable.flag_br
    ),

    CANADIAN_DOLLAR(
        Currency.CANADIAN_DOLLAR,
        R.string.currency_canadian_dollar_code,
        R.string.currency_australian_dollar_name,
        R.drawable.flag_ca
    ),

    SWISS_FRANC(
        Currency.SWISS_FRANC,
        R.string.currency_swiss_franc_code,
        R.string.currency_swiss_franc_name,
        R.drawable.flag_ch
    ),

    CHINESE_YUAN(
        Currency.CHINESE_YUAN,
        R.string.currency_chinese_yuan_code,
        R.string.currency_chinese_yuan_name,
        R.drawable.flag_cn
    ),

    CZECH_KORUNA(
        Currency.CZECH_KORUNA,
        R.string.currency_czech_koruna_code,
        R.string.currency_czech_koruna_name,
        R.drawable.flag_cz
    ),

    DANISH_KRONE(
        Currency.DANISH_KRONE,
        R.string.currency_danish_krone_code,
        R.string.currency_danish_krone_name,
        R.drawable.flag_dk
    ),

    EURO(
        Currency.EURO,
        R.string.currency_euro_code,
        R.string.currency_euro_name,
        R.drawable.flag_eu
    ),

    POUND_STERLING(
        Currency.POUND_STERLING,
        R.string.currency_pound_sterling_code,
        R.string.currency_pound_sterling_name,
        R.drawable.flag_uk
    ),

    HONG_KONG_DOLLAR(
        Currency.HONG_KONG_DOLLAR,
        R.string.currency_hong_kong_dollar_code,
        R.string.currency_hong_kong_dollar_name,
        R.drawable.flag_hk
    ),

    CROATIAN_KUNA(
        Currency.CROATIAN_KUNA,
        R.string.currency_croatian_kuna_code,
        R.string.currency_croatian_kuna_name,
        R.drawable.flag_hr
    ),

    HUNGARIAN_FORINT(
        Currency.HUNGARIAN_FORINT,
        R.string.currency_hungarian_forint_code,
        R.string.currency_hungarian_forint_name,
        R.drawable.flag_hu
    ),

    INDONESIAN_RUPIAH(
        Currency.INDONESIAN_RUPIAH,
        R.string.currency_indonesian_rupiah_code,
        R.string.currency_indonesian_rupiah_name,
        R.drawable.flag_id
    ),

    ISRAELI_NEW_SHEKEL(
        Currency.ISRAELI_NEW_SHEKEL,
        R.string.currency_israeli_new_shekel_code,
        R.string.currency_israeli_new_shekel_name,
        R.drawable.flag_il
    ),

    INDIAN_RUPEE(
        Currency.INDIAN_RUPEE,
        R.string.currency_indian_rupee_code,
        R.string.currency_indian_rupee_name,
        R.drawable.flag_in
    ),

    ICELANDIC_KRONA(
        Currency.ICELANDIC_KRONA,
        R.string.currency_icelandic_krona_code,
        R.string.currency_icelandic_krona_name,
        R.drawable.flag_is
    ),

    JAPANESE_YEN(
        Currency.JAPANESE_YEN,
        R.string.currency_japanese_yen_code,
        R.string.currency_japanese_yen_name,
        R.drawable.flag_jp
    ),

    SOUTH_KOREAN_WON(
        Currency.SOUTH_KOREAN_WON,
        R.string.currency_south_korean_won_code,
        R.string.currency_south_korean_won_name,
        R.drawable.flag_sk
    ),

    MEXICAN_PESO(
        Currency.MEXICAN_PESO,
        R.string.currency_mexican_peso_code,
        R.string.currency_mexican_peso_name,
        R.drawable.flag_mx
    ),

    MALAYSIAN_RINGGIT(
        Currency.MALAYSIAN_RINGGIT,
        R.string.currency_malaysian_ringgit_code,
        R.string.currency_malaysian_ringgit_name,
        R.drawable.flag_my
    ),

    NORWEGIAN_KRONE(
        Currency.NORWEGIAN_KRONE,
        R.string.currency_norwegian_krone_code,
        R.string.currency_norwegian_krone_name,
        R.drawable.flag_no
    ),

    NEW_ZEALAND_DOLLAR(
        Currency.NEW_ZEALAND_DOLLAR,
        R.string.currency_new_zealand_dollar_code,
        R.string.currency_new_zealand_dollar_name,
        R.drawable.flag_nz
    ),

    PHILIPPINE_PESO(
        Currency.PHILIPPINE_PESO,
        R.string.currency_philippine_peso_code,
        R.string.currency_philippine_peso_name,
        R.drawable.flag_ph
    ),

    POLISH_ZLOTY(
        Currency.POLISH_ZLOTY,
        R.string.currency_polish_zloty_code,
        R.string.currency_polish_zloty_name,
        R.drawable.flag_pl
    ),

    ROMANIAN_LEU(
        Currency.ROMANIAN_LEU,
        R.string.currency_romanian_leu_code,
        R.string.currency_romanian_leu_name,
        R.drawable.flag_ro
    ),

    RUSSIAN_RUBLE(
        Currency.RUSSIAN_RUBLE,
        R.string.currency_russian_ruble_code,
        R.string.currency_russian_ruble_name,
        R.drawable.flag_ru
    ),

    SWEDISH_KRONA(
        Currency.SWEDISH_KRONA,
        R.string.currency_swedish_krona_code,
        R.string.currency_swedish_krona_name,
        R.drawable.flag_se
    ),

    SINGAPORE_DOLLAR(
        Currency.SINGAPORE_DOLLAR,
        R.string.currency_singapore_dollar_code,
        R.string.currency_singapore_dollar_name,
        R.drawable.flag_sg
    ),

    THAI_BAHT(
        Currency.THAI_BAHT,
        R.string.currency_thai_baht_code,
        R.string.currency_thai_baht_name,
        R.drawable.flag_th
    ),

    TURKISH_LIRA(
        Currency.TURKISH_LIRA,
        R.string.currency_turkish_lira_code,
        R.string.currency_turkish_lira_name,
        R.drawable.flag_tr
    ),

    UNITED_STATES_DOLLAR(
        Currency.UNITED_STATES_DOLLAR,
        R.string.currency_united_states_dollar_code,
        R.string.currency_united_states_dollar_name,
        R.drawable.flag_us
    ),

    SOUTH_AFRICAN_RAND(
        Currency.SOUTH_AFRICAN_RAND,
        R.string.currency_south_african_rand_code,
        R.string.currency_south_african_rand_name,
        R.drawable.flag_za
    ),

    UNKNOWN(
        Currency.UNKNOWN,
        R.string.currency_unknown_code,
        R.string.currency_unknown_name,
        R.drawable.flag__unknown
    );

    companion object {

        private val currencyToUiCurrencyMapping: Map<Currency, UiCurrency> by lazy(LazyThreadSafetyMode.NONE) {
            mutableMapOf<Currency, UiCurrency>().apply {
                UiCurrency.values()
                    .filter { UiCurrency.UNKNOWN != it }
                    .forEach { this[it.currency] = it }
            }
        }

        fun fromCurrency(currency: Currency) = currencyToUiCurrencyMapping[currency] ?: UiCurrency.UNKNOWN

    }

}