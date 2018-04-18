package com.dan.architecurecomponentkotlin.ui.common

import android.support.v4.app.FragmentManager
import com.dan.architecurecomponentkotlin.MainActivity
import com.dan.architecurecomponentkotlin.R
import com.dan.architecurecomponentkotlin.ui.image.ImageFragment
import javax.inject.Inject

class NavigationController @Inject constructor(mainActivity: MainActivity) {
    private var containerId: Int = 0
    private var fragmentManager: FragmentManager

    init {
        containerId = R.id.container
        fragmentManager = mainActivity.supportFragmentManager
    }

    fun navigateToImage() {
        val searchFragment = ImageFragment()
        fragmentManager.beginTransaction()
                .replace(containerId, searchFragment)
                .commitAllowingStateLoss()
    }
}