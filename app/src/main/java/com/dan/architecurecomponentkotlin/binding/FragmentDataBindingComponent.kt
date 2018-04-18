package com.dan.architecurecomponentkotlin.binding

import android.databinding.DataBindingComponent
import android.support.v4.app.Fragment

class FragmentDataBindingComponent constructor(fragment: Fragment) : DataBindingComponent {

    private var adapter: FragmentBindingAdapters = FragmentBindingAdapters(fragment)

    override fun getFragmentBindingAdapters(): FragmentBindingAdapters = adapter

    override fun getBindingAdapters(): BindingAdapters = BindingAdapters()
}