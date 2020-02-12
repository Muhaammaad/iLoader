package  com.muhaammaad.iloaderapplication.ui.main.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.muhaammaad.iloaderapplication.R

/**
 * Launcher Activity
 * Responsible to display photos list fragment to show photos list
 */
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
