package ro.lazarl.converter.converter

import android.view.LayoutInflater
import android.view.ViewGroup
import ro.lazarl.converter.FragmentScope
import javax.inject.Inject

@FragmentScope
class ConverterViewsFactory @Inject constructor(
    private val layoutInflater: LayoutInflater
) {

    fun getConverterView(
        parent: ViewGroup?
    ): ConverterView = ConverterView(layoutInflater, parent, this)

    fun getConverterCurrencyItemView(
        parent: ViewGroup?
    ): ConverterCurrencyItemView = ConverterCurrencyItemView(layoutInflater, parent)

}