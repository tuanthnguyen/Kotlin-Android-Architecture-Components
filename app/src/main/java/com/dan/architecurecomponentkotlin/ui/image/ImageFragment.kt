package com.dan.architecurecomponentkotlin.ui.image

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dan.architecurecomponentkotlin.R
import com.dan.architecurecomponentkotlin.binding.FragmentDataBindingComponent
import com.dan.architecurecomponentkotlin.databinding.ImageFragmentBinding
import com.dan.architecurecomponentkotlin.di.Injectable
import com.dan.architecurecomponentkotlin.ui.common.NavigationController
import com.dan.architecurecomponentkotlin.ui.common.RetryCallback
import com.dan.architecurecomponentkotlin.util.AutoClearedValue
import com.dan.architecurecomponentkotlin.vo.Image
import javax.inject.Inject

class ImageFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var navigationController: NavigationController

    private val dataBindingComponent = FragmentDataBindingComponent(this)

    private lateinit var binding: AutoClearedValue<ImageFragmentBinding>

    private lateinit var adapter: AutoClearedValue<ImagesAdapter>

    private lateinit var imageViewModel: ImageViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dataBinding = DataBindingUtil
                .inflate<ImageFragmentBinding>(inflater, R.layout.image_fragment, container, false,
                        dataBindingComponent)
        binding = AutoClearedValue(this, dataBinding)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        imageViewModel = ViewModelProviders.of(this, viewModelFactory).get(ImageViewModel::class.java)
        initRecyclerView()
        val rvAdapter = ImagesAdapter(dataBindingComponent, object : ImagesAdapter.ImageClickCallback{
            override fun onClick(image: Image) {
                Toast.makeText(activity, image.id, Toast.LENGTH_SHORT).show()
            }
        })
        binding.get()?.imageRecyclerView!!.adapter = rvAdapter
        adapter = AutoClearedValue(this, rvAdapter)

        binding.get()?.callback = object : RetryCallback{
            override fun retry() {
                Toast.makeText(activity, getString(R.string.retry), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initRecyclerView() {
        imageViewModel.images.observe(this, Observer  {
            binding.get()?.resource = it
            adapter.get()?.replace(it?.data)
            binding.get()?.executePendingBindings()
        })
    }
}