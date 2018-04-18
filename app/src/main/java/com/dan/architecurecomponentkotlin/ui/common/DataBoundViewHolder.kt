package com.dan.architecurecomponentkotlin.ui.common

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

class DataBoundViewHolder<out T : ViewDataBinding> internal constructor(val binding: T) : RecyclerView.ViewHolder(binding.root)
