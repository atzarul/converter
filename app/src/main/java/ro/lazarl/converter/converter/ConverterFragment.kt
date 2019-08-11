package ro.lazarl.converter.converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ConverterFragment : DaggerFragment() {

    companion object {
        fun newInstance() = ConverterFragment()
    }

    @Inject
    internal lateinit var viewFactory: ConverterViewsFactory

    @Inject
    internal lateinit var viewModel: ConverterViewModel

    private var view: ConverterView? = null
    private var viewStatesDisposable: Disposable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = viewFactory.getConverterView(container).let {
        view = it
        return@let it.getRootView()
    }

    override fun onStart() {
        super.onStart()

        view?.also { view ->
            viewModel.bindUserInteractionEvents(view.getUserInteractionEvents())
            viewStatesDisposable = viewModel.viewStates().subscribe(view::render)
        }
        viewModel.onStart()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewStatesDisposable?.dispose()
        viewStatesDisposable = null
        view = null
    }

}