package ro.lazarl.converter.converter

import android.content.Context
import android.text.InputFilter
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.view.focusChanges
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import ro.lazarl.converter.R
import ro.lazarl.converter.common.UiView
import java.text.DecimalFormatSymbols

class ConverterCurrencyItemView(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : UiView<ConverterCurrencyItemViewState, ConverterUserInteractionEvents> {

    private val rootView: View = layoutInflater.inflate(R.layout.converter_currency_item, parent, false)
    private val icon: ImageView = rootView.findViewById(R.id.circle_image_view_currency_flag)
    private val code: TextView = rootView.findViewById(R.id.text_view_currency_code)
    private val name: TextView = rootView.findViewById(R.id.text_view_currency_name)
    private val amount: TextInputEditText = rootView.findViewById(R.id.edit_text_currency_amount)

    private val tenDigitsAndTwoDecimalsInputFilter = arrayOf(TenDigitsAndTwoDecimalsInputFilter())

    private var areInteractionsEnabled: Boolean = false
    private var state: ConverterCurrencyItemViewState? = null

    override fun getRootView(): View = rootView

    override fun getUserInteractionEvents(): Observable<ConverterUserInteractionEvents> =
        Observable.merge(
            rootView.clicks()
                .filter { false == state?.isBase }
                .doOnNext {
                    amount.requestFocus()
                    requestKeyboard(amount)
                },
            amount.focusChanges()
                .filter { hasFocus -> hasFocus && false == state?.isBase },
            amount.textChanges()
                .filter { true == state?.isBase }
        ).filter {
            areInteractionsEnabled
        }.map {
            state?.let { state ->
                ConverterUserInteractionEvents.AmountChanged(amount.text.toString(), state.uiCurrency.currency)
            }
        }

    override fun render(nextState: ConverterCurrencyItemViewState) {
        areInteractionsEnabled = false

        if (state?.uiCurrency != nextState.uiCurrency) {
            icon.setImageResource(nextState.uiCurrency.iconResId)
            code.setText(nextState.uiCurrency.codeResId)
            name.setText(nextState.uiCurrency.nameResId)
        }
        if (null == state || nextState.amount != amount.text?.toString()) {
            amount.setText(nextState.amount)
        }
        amount.filters = if (nextState.isBase) tenDigitsAndTwoDecimalsInputFilter else emptyArray()

        state = nextState

        areInteractionsEnabled = true
    }

    private fun requestKeyboard(forView: View) =
        (forView
            .context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?)
            ?.showSoftInput(forView, InputMethodManager.SHOW_IMPLICIT)

}

private const val EMPTY_STRING = ""
private const val INVALID_INDEX = -1

private class TenDigitsAndTwoDecimalsInputFilter : InputFilter {

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        if (EMPTY_STRING == source) {
            /*
             * Not inserting anything new. If the destination was previously valid, it will also be valid now.
             */
            return null
        }

        val result = StringBuilder(dest).insert(dstart, source).toString()

        var digitsAfterDecimalPoint = 0
        val decimalPointPosition = result.indexOf(DecimalFormatSymbols.getInstance().decimalSeparator)
        val hasDecimalPoint = INVALID_INDEX < decimalPointPosition
        if (hasDecimalPoint) {
            digitsAfterDecimalPoint = result.length - decimalPointPosition - 1
            if (2 < digitsAfterDecimalPoint) {
                return EMPTY_STRING
            }
        }

        /*
         * Also deduct the decimal point if it is present.
         */
        val digitsBeforeDecimalPoint =
            result.length - digitsAfterDecimalPoint - (if (hasDecimalPoint) -1 else 0)
        return if (10 < digitsBeforeDecimalPoint) EMPTY_STRING else null
    }

}