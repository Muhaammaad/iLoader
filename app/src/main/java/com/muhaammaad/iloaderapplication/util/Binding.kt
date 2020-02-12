package com.muhaammaad.iloaderapplication.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muhaammaad.iloaderapplication.R
import com.muhaammaad.iloaderapplication.model.Picture
import com.muhaammaad.iloaderapplication.model.User
import com.muhaammaad.iloaderapplication.ui.main.adapter.PictureListAdapter

@BindingAdapter("items")
fun setItems(recyclerView: RecyclerView, items: Map<Long, Picture>) {
    val adapter = recyclerView.adapter as? PictureListAdapter
    adapter?.addList(items.values)
}

@BindingAdapter("loadImage")
fun loadImage(
    view: ImageView,
    mCurrentPicture: Picture?
) {
    view.setImageResource(R.drawable.ic_placeholder)
}


@BindingAdapter("loadAvatar")
fun loadAvatar(
    view: ImageView,
    user: User?
) {
    view.setImageResource(R.drawable.ic_person_placeholder)
}