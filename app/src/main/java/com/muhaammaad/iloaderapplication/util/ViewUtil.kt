package com.muhaammaad.iloaderapplication.util

import android.view.View
import com.google.android.material.snackbar.Snackbar


fun showShortDurationSnackBar(view: View, msg: String) {
    Snackbar.make(
        view, msg,
        Snackbar.LENGTH_SHORT
    ).show()
}

fun showLongDurationSnackBar(view: View, msg: String) {
    Snackbar.make(
        view, msg,
        Snackbar.LENGTH_LONG
    ).show()
}