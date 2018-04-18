package com.dan.architecurecomponentkotlin.di

import com.dan.architecurecomponentkotlin.ui.image.ImageFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/*
 * Created by thanhtuan on 4/11/18.
 */
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): ImageFragment
}