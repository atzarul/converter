package ro.lazarl.converter.converter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import ro.lazarl.converter.R
import ro.lazarl.converter.common.UiView

class ConverterView(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    viewFactory: ConverterViewsFactory
) : UiView<ConverterViewState, ConverterUserInteractionEvents> {

    private val rootView: View = layoutInflater.inflate(R.layout.converter_view, parent, false)

    private val currencyRecycler: RecyclerView = rootView.findViewById(R.id.recycler_view_converter_currency_list)
    private val currencyListAdapter: ConverterCurrencyListAdapter

    private val loadingIndicator: ProgressBar = rootView.findViewById(R.id.progress_bar_converter_loading_indicator)

    init {
        currencyRecycler.itemAnimator = null
        currencyRecycler.setHasFixedSize(true)
        currencyRecycler.layoutManager = LinearLayoutManager(currencyRecycler.context)
        currencyRecycler.adapter = ConverterCurrencyListAdapter(viewFactory).also { adapter ->
            currencyListAdapter = adapter
        }
    }

    override fun getRootView(): View = rootView

    override fun getUserInteractionEvents(): Observable<ConverterUserInteractionEvents> =
        currencyListAdapter.getUserInteractionEvents()

    override fun render(nextState: ConverterViewState) {
        loadingIndicator.isVisible = nextState.isLoading
        currencyListAdapter.submitList(nextState.amounts)
    }

}