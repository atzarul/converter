package ro.lazarl.converter

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ro.lazarl.converter.converter.ConverterActivity
import ro.lazarl.converter.converter.ConverterActivityModule

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            ConverterActivityModule::class
        ]
    )
    abstract fun injectConverterActivity(): ConverterActivity

}