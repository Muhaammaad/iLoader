package com.muhaammaad.iloaderapplication.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.muhaammaad.iloaderapplication.R
import com.muhaammaad.iloaderapplication.databinding.PictureItemLayoutBinding
import com.muhaammaad.iloaderapplication.model.Picture
import java.util.*

/**
 *  Adapter responsible to handle photos
 */
class PictureListAdapter// Public Constructor
    (
    /**
     * Context and reference of the arrayList
     */
    private val mContext: Context, pictures: Collection<Picture>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mPictureDetailArrayList = ArrayList<Picture>()

    init {
        mPictureDetailArrayList.addAll(pictures)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val pictureListItemHolder = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.picture_item_layout, parent, false
        ) as PictureItemLayoutBinding
        return PictureListItemHolder(pictureListItemHolder)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PictureListItemHolder).pictureItemLayoutBinding.photo =
            mPictureDetailArrayList[position]
    }

    override fun getItemCount(): Int {
        return mPictureDetailArrayList.size
    }

    fun addList(values: Collection<Picture>) {
        val positionStart = mPictureDetailArrayList.size
        mPictureDetailArrayList.addAll(values)
        this.notifyItemRangeInserted(positionStart + 1, values.size);
    }

    /**
     * View holder responsible for photo item binding
     */
    internal inner class PictureListItemHolder(val pictureItemLayoutBinding: PictureItemLayoutBinding) :
        RecyclerView.ViewHolder(pictureItemLayoutBinding.root)
}

