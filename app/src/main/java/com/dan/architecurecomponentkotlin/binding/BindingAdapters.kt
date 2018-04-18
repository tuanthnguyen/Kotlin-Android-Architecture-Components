package com.dan.architecurecomponentkotlin.binding

import android.databinding.BindingAdapter
import android.view.View

class BindingAdapters {
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }
}