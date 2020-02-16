package com.muhaammaad.iloaderapplication.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muhaammaad.iloader.base.ILoader
import com.muhaammaad.iloader.model.DataRequest
import com.muhaammaad.iloaderapplication.model.Picture
import com.muhaammaad.iloaderapplication.ui.main.adapter.PictureListAdapter


/**
 * Loads data from provided [items] and sets into provided [recyclerView]
 *
 * @param recyclerView RecyclerView in which source should be set
 * @param items map having ids and Pictures
 */
@BindingAdapter("items")
fun setItems(recyclerView: RecyclerView, items: Map<String, Picture>) {
    val adapter = recyclerView.adapter as? PictureListAdapter
    adapter?.addList(items.values)
}

/**
 * Loads data from provided [src] and sets into provided [view]
 *
 * @param src can be url string or [DataRequest]
 * @param view image view in which source should be set
 * @param placeholder default image drawable for provided [view]
 * @param errorPlaceholder default image drawable for provided [view] in case of error
 * @param loader injected loader reference
 */

@BindingAdapter(value = ["src", "placeholder", "errorPlaceholder", "loader"], requireAll = false)
fun loadImage(
    view: ImageView,
    src: String,
    placeholder: Drawable,
    errorPlaceholder: Drawable,
    loader: ILoader?
) {
    loader?.load(
        source = src
        , view = view
        , placeholder = placeholder, errorPlaceholder = errorPlaceholder
    ) ?: run {
        ILoader.load(
            source = src
            , view = view
            , placeholder = placeholder, errorPlaceholder = errorPlaceholder
        )
    }
}
