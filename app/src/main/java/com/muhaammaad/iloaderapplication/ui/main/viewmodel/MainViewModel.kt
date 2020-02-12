package com.muhaammaad.iloaderapplication.ui.main.viewmodel

import android.app.Application
import androidx.databinding.ObservableArrayMap
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import com.muhaammaad.iloaderapplication.model.Picture
import com.muhaammaad.iloaderapplication.model.ProfileImage
import com.muhaammaad.iloaderapplication.model.User
import com.muhaammaad.iloaderapplication.util.SingleLiveEvent
import kotlin.random.Random

/**
 * ViewModel to handle Main Activity view and pictureListFragment
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    //region Member properties
    val mImagesLoading: SingleLiveEvent<String> = SingleLiveEvent()
    var mPictureDetailsList = ObservableArrayMap<Long, Picture>()
    var visibleItemCounter: Int = 0
    val pageOffset: Int = 10
    var totalItemCounter: Int = 0
    var firstVisibleItem: Int = 0
    var pageCounter = 1
    var previousTotalItem = 0
    val visibleThresholdItem = 4
    var processingNewItem = true
    var index = 0
    var progressDialog: ObservableBoolean = ObservableBoolean()
    //endregion

    //region intialiazer
    init {
        getPictureListData()
    }
    //endregion
    //region Image data Fetching
    /***
     * fetch and set images
     */
    fun getPictureListData() {
        setLoadingMessage("Loading More Pictures...")
        setProgressDialog(true)
        getDummyData().let {
            setProgressDialog(false)
            mPictureDetailsList.putAll(it)
        }

    }

    /***
     * returns dummy images
     */
    private fun getDummyData(): HashMap<Long, Picture> {
        val tempPictureDetailsList = HashMap<Long, Picture>();
        for (i in 0..pageOffset) {
            tempPictureDetailsList.put(
                index.toLong(),
                Picture(
                    user = User(
                        profileImage = ProfileImage(
                            small = if (Random.nextInt(
                                    0,
                                    10
                                ) % 2 == 0
                            ) "0" else ""
                        ),
                        id = index.toString(),
                        username = if (Random.nextInt(
                                0,
                                10
                            ) % 2 == 0
                        ) "UserName".plus(index) else "",
                        name = if (Random.nextInt(0, 10) % 2 == 0) "Name".plus(index) else ""
                    )
                )
            )
            index++
        }
        return tempPictureDetailsList
    }

    private fun setLoadingMessage(msg: String) {
        mImagesLoading.value = msg
    }
    //endregion

    //region general functions
    private fun setProgressDialog(show: Boolean) {
        progressDialog.set(show)
    }

    /***
     *  when ViewModel is no longer used and will be destroyed
     */
    override fun onCleared() {
        super.onCleared()
    }
    //endregion
}