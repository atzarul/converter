package ro.lazarl.converter.converter

import android.content.Context
import android.view.LayoutInflater
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import ro.lazarl.converter.ActivityContext
import ro.lazarl.converter.ActivityScope
import ro.lazarl.converter.FragmentScope

@Module
abstract class ConverterActivityModule {

    @Binds
    @ActivityContext
    abstract fun provideActivityContext(activity: ConverterActivity): Context

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            ConverterFragmentModule::class
        ]
    )
    abstract fun injectConverterFragment(): ConverterFragment

    @Module
    companion object {

        @JvmStatic
        @ActivityScope
        @Provides
        fun provideLayoutInflater(@ActivityContext context: Context): LayoutInflater = LayoutInflater.from(context)

    }

}