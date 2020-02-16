package com.muhaammaad.iloaderapplication.ui.main.viewmodel

import android.app.Application
import androidx.databinding.ObservableArrayMap
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.muhaammaad.iloader.base.ILoader
import com.muhaammaad.iloader.callback.CompletionCallback
import com.muhaammaad.iloader.util.Mapper
import com.muhaammaad.iloaderapplication.R
import com.muhaammaad.iloaderapplication.mapper.PictureListMapper
import com.muhaammaad.iloaderapplication.model.Picture
import com.muhaammaad.iloaderapplication.util.SingleLiveEvent

/**
 * ViewModel to handle Main Activity view and pictureListFragment
 */
class MainViewModel(private val iLoader: ILoader, private val application: Application) : ViewModel() {

    //region Member properties
    val mImagesLoading: SingleLiveEvent<String> = SingleLiveEvent()
    var mPictureDetailsList = ObservableArrayMap<String, Picture>()
    var progressDialog: ObservableBoolean = ObservableBoolean()
    //endregion

    //region intialiazer
    init {
        getPictureListData()

    }
    //endregion
    //region Image data Fetching
    /**
     * fetch images
     */
    fun getPictureListData() {
        setLoadingMessage(
            application.resources.getString(
                R.string.loading_image
            )
        )
        setProgressDialog(true)
        loadData()
    }

    /**
     * Gets Response from URL
     * Maps it into pictures list using [Mapper] into [PictureListMapper]
     * Gets List of pictures after mapping in [CompletionCallback]
     * Sets List of pictures after mapping
     */
    private fun loadData() {
        iLoader.load(
            IMAGES_URL,
            PictureListMapper(),
            object :
                CompletionCallback<List<Picture>?, Boolean> {
                override fun completed(response: List<Picture>?, isSuccess: Boolean) {
                    populate(response, isSuccess)
                }

            })

    }
    //endregion

    //region general functions
    private fun setLoadingMessage(msg: String) {
        mImagesLoading.value = msg
    }

    private fun setProgressDialog(show: Boolean) {
        progressDialog.set(show)
    }

    /**
     * Populate the mapped data
     *
     * @param response List of pictures mapped from response
     * @param isSuccess Mapping status
     */
    private fun populate(response: List<Picture>?, isSuccess: Boolean) {
        setLoadingMessage(
            if (isSuccess) application.resources.getString(
                R.string.fetch_image_success
            ) else application.resources.getString(
                R.string.fetch_image_fail
            )
        )
        setProgressDialog(false)
        response?.let { pictures ->
            val tempPictureDetailsList = HashMap<String, Picture>()
            for (picture in pictures) {
                tempPictureDetailsList[picture.id] = picture
            }
            tempPictureDetailsList.let {
                mPictureDetailsList.putAll(it)
            }
        }
    }
    //endregion

    companion object {
        private var IMAGES_URL = "https://pastebin.com/raw/wgkJgazE"
    }
}