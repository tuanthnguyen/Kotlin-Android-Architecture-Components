package com.dan.architecurecomponentkotlin.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.dan.architecurecomponentkotlin.ui.image.ImageViewModel
import com.dan.architecurecomponentkotlin.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/*
 * Created by thanhtuan on 4/11/18.
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ImageViewModel::class)
    abstract fun bindImageViewModel(imageViewModel: ImageViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}