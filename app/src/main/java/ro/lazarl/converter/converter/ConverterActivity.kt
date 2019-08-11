package ro.lazarl.converter.converter

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import ro.lazarl.converter.R

class ConverterActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.converter_activity)

        if (null == savedInstanceState) {
            supportFragmentManager
                .beginTransaction()
                .add(
                    R.id.frame_converter,
                    ConverterFragment.newInstance()
                )
                .commit()
        }
    }

}