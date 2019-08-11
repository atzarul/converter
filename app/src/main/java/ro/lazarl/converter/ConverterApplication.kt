package ro.lazarl.converter

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ConverterApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent
            .factory()
            .create(this)

}