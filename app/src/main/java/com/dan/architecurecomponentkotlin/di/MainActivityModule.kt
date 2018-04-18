package com.dan.architecurecomponentkotlin.di

import com.dan.architecurecomponentkotlin.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/*
 * Created by thanhtuan on 4/11/18.
 */
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}