package ro.lazarl.converter.converter

import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class ConverterCurrencyListAdapter(
    private val viewFactory: ConverterViewsFactory
) : ListAdapter<
        ConverterCurrencyItemViewState,
        ConverterCurrencyListAdapter.ConverterCurrencyItemViewHolder>
    (ConverterCurrencyItemViewDiffCallback()) {

    private val userInteractionEvents = PublishSubject.create<ConverterUserInteractionEvents>()

    init {
        setHasStableIds(true)
    }

    class ConverterCurrencyItemViewHolder(
        val view: ConverterCurrencyItemView
    ) : RecyclerView.ViewHolder(view.getRootView())

    override fun getItemId(position: Int): Long = getItem(position).uiCurrency.currency.ordinal.toLong()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConverterCurrencyItemViewHolder =
        ConverterCurrencyItemViewHolder(viewFactory.getConverterCurrencyItemView(parent)).apply {
            view.getUserInteractionEvents().subscribe(userInteractionEvents)
        }

    override fun onBindViewHolder(
        holder: ConverterCurrencyItemViewHolder,
        position: Int
    ) = holder.view.render(getItem(position))

    fun getUserInteractionEvents(): Observable<ConverterUserInteractionEvents> = userInteractionEvents

}

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
class ConverterCurrencyItemViewDiffCallback :
    DiffUtil.ItemCallback<ConverterCurrencyItemViewState>() {

    override fun areItemsTheSame(
        old: ConverterCurrencyItemViewState,
        new: ConverterCurrencyItemViewState
    ): Boolean = old.uiCurrency.currency == new.uiCurrency.currency

    override fun areContentsTheSame(
        old: ConverterCurrencyItemViewState,
        new: ConverterCurrencyItemViewState
    ): Boolean = old == new

}