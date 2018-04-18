package com.dan.architecurecomponentkotlin.ui.image

import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dan.architecurecomponentkotlin.R
import com.dan.architecurecomponentkotlin.databinding.ItemImageBinding
import com.dan.architecurecomponentkotlin.ui.common.DataBoundListAdapter
import com.dan.architecurecomponentkotlin.vo.Image

class ImagesAdapter constructor(private val dataBindingComponent: DataBindingComponent,
                                private val imageClickCallback: ImageClickCallback) : DataBoundListAdapter<Image, ItemImageBinding>() {
    override fun createBinding(parent: ViewGroup): ItemImageBinding {
        val binding = DataBindingUtil
                .inflate<ItemImageBinding>(LayoutInflater.from(parent.context), R.layout.item_image,
                        parent, false, dataBindingComponent)
        binding.root.setOnClickListener {
            val image = binding.image
            if (image != null) {
                imageClickCallback.onClick(image)
            }
        }
        return binding
    }

    override fun bind(binding: ItemImageBinding, item: Image) {
        binding.image = item
    }

    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.downloadUrl == newItem.downloadUrl
    }

    interface ImageClickCallback {
        fun onClick(image: Image)
    }
}
