package ro.lazarl.converter

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Root of the injection hierarchy.
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBindingModule::class,
        ApplicationModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<ConverterApplication> {
    @Component.Factory
    interface Factory : AndroidInjector.Factory<ConverterApplication>
}