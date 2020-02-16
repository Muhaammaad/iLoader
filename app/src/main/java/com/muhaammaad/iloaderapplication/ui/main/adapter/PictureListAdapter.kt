package com.muhaammaad.iloaderapplication.ui.main.adapter

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.muhaammaad.iloader.base.ILoader
import com.muhaammaad.iloaderapplication.R
import com.muhaammaad.iloaderapplication.databinding.PictureItemLayoutBinding
import com.muhaammaad.iloaderapplication.model.Picture
import kotlinx.android.synthetic.main.picture_item_layout.view.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*

/**
 *  Adapter responsible to handle photos
 */
class PictureListAdapter// Public Constructor
    (
    /**
     * Context and reference of the arrayList
     */
    pictures: Collection<Picture>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), KoinComponent {

    private val mPictureDetailArrayList = ArrayList<Picture>()
    private val iLoader: ILoader by inject()

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
        (holder as? PictureListItemHolder)?.let {
            it.pictureItemLayoutBinding.iLoader = iLoader
            it.pictureItemLayoutBinding.photo =
                mPictureDetailArrayList[position]
            it.pictureItemLayoutBinding.root.setOnClickListener { result -> revertViewAlpa(result.crdLyLike) }
        }
    }

    private fun revertViewAlpa(view: View) {
        val shown = view.alpha == 1f
        val startHeight: Float = if (shown) 1f else 0f
        val targetHeight: Float = if (shown) 0f else 1f
        val animator = ObjectAnimator.ofFloat(view, View.ALPHA, startHeight, targetHeight)
        animator.interpolator = AccelerateInterpolator()
        animator.duration = 200
        animator.start()
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

