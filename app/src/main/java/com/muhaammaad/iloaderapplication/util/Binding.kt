package com.muhaammaad.iloaderapplication.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muhaammaad.iloader.base.ILoader
import com.muhaammaad.iloaderapplication.model.Picture
import com.muhaammaad.iloaderapplication.ui.main.adapter.PictureListAdapter


@BindingAdapter("items")
fun setItems(recyclerView: RecyclerView, items: Map<String, Picture>) {
    val adapter = recyclerView.adapter as? PictureListAdapter
    adapter?.addList(items.values)
}


@BindingAdapter(value = ["src", "placeholder", "errorPlaceholder"], requireAll = false)
fun loadImage(
    view: ImageView,
    src: String,
    placeholder: Drawable,
    errorPlaceholder: Drawable
) {
    ILoader.load(
        source = src
        , view = view
        , placeholder = placeholder, errorPlaceholder = errorPlaceholder
    )
}
