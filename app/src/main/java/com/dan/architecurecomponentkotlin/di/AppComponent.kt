package com.dan.architecurecomponentkotlin.di

import android.app.Application
import com.dan.architecurecomponentkotlin.ImageApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/*
 * Created by thanhtuan on 4/11/18.
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, MainActivityModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(imageApp: ImageApp)
}
