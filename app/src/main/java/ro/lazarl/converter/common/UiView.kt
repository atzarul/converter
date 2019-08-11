package ro.lazarl.converter.common

import android.view.View
import io.reactivex.Observable

/**
 * @param VS ViewState type.
 * @param UIE UserInteractionEvents type.
 */
interface UiView<in VS, UIE> {

    fun getRootView(): View

    fun getUserInteractionEvents(): Observable<UIE>

    fun render(viewState: VS)

}