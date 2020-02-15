package  com.muhaammaad.iloaderapplication.ui.main.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.muhaammaad.iloaderapplication.R
import com.muhaammaad.iloaderapplication.ui.main.fragment.PictureListFragment

/**
 * Launcher Activity
 * Responsible to display [PictureListFragment] to show photos list
 */
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
