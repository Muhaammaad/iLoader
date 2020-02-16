package  com.muhaammaad.iloaderapplication.ui.main.fragment


import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.muhaammaad.iloaderapplication.R
import com.muhaammaad.iloaderapplication.databinding.FragmentPictureListBinding
import com.muhaammaad.iloaderapplication.ui.main.adapter.PictureListAdapter
import com.muhaammaad.iloaderapplication.ui.main.viewmodel.MainViewModel
import com.muhaammaad.iloaderapplication.util.EndlessRecyclerViewScrollListener
import com.muhaammaad.iloaderapplication.util.showShortDurationSnackBar
import org.koin.android.viewmodel.ext.android.viewModel


/**
 *  fragment responsible to show photos list
 */
class PictureListFragment : Fragment() {

    private val ITEMS_PER_ROW: Int = 2
    private val sharedViewModel: MainViewModel by viewModel()
    private lateinit var mPictureListAdapter: PictureListAdapter
    private lateinit var binding: FragmentPictureListBinding
    private lateinit var mLayoutManager: StaggeredGridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_picture_list, container, false)
        binding.viewmodel = sharedViewModel

        mPictureListAdapter =
            PictureListAdapter(sharedViewModel.mPictureDetailsList.values)
        mLayoutManager =
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                StaggeredGridLayoutManager(
                    ITEMS_PER_ROW.plus(ITEMS_PER_ROW),
                    StaggeredGridLayoutManager.VERTICAL
                )
            } else {
                StaggeredGridLayoutManager(ITEMS_PER_ROW, StaggeredGridLayoutManager.VERTICAL)
            }
        binding.recyclerView.apply {
            layoutManager = mLayoutManager
            adapter = mPictureListAdapter
        }

        setScrollListenerToLoadMoreItems()
        initObservers()
        return binding.root
    }

    /**
     *  initializes all the observables from viewmodel
     */
    private fun initObservers() {
        observeImagesLoading()
    }

    /**
     *  Observes and inform user about the status of loading images from viewmodel
     */
    private fun observeImagesLoading() {
        sharedViewModel.mImagesLoading.observe(this, Observer {
            showShortDurationSnackBar(binding.recyclerView, it)
        })
    }

    /**
     *  Listener for loading more items if recycler view is scrolled to bottom
     */
    private fun setScrollListenerToLoadMoreItems() {
        val scrollListener = object : EndlessRecyclerViewScrollListener(mLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    sharedViewModel.getPictureListData()
                }
            }
        }
        binding.recyclerView.addOnScrollListener(scrollListener)
    }
}