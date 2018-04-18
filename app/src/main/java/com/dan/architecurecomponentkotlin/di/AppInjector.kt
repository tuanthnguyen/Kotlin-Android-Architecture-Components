package com.dan.architecurecomponentkotlin.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.dan.architecurecomponentkotlin.ImageApp
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

/*
 * Created by thanhtuan on 4/11/18.
 */
class AppInjector {
    private constructor()

    companion object {
        fun init(imageApp: ImageApp) {
            DaggerAppComponent.builder().application(imageApp)
                    .build().inject(imageApp)
            imageApp.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                override fun onActivityCreated(activity: Activity, p1: Bundle?) {
                    handleActivity(activity)
                }

                override fun onActivityPaused(p0: Activity?) {
                }

                override fun onActivityResumed(p0: Activity?) {
                }

                override fun onActivityStarted(p0: Activity?) {
                }

                override fun onActivityDestroyed(p0: Activity?) {
                }

                override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {
                }

                override fun onActivityStopped(p0: Activity?) {
                }
            })
        }

        fun handleActivity(activity: Activity) {
            if (activity is HasSupportFragmentInjector) {
                AndroidInjection.inject(activity)
            }
            if (activity is FragmentActivity) {
                activity.supportFragmentManager
                        .registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
                            override fun onFragmentCreated(fm: FragmentManager?, f: Fragment, savedInstanceState: Bundle?) {
                                if (f is Injectable) {
                                    AndroidSupportInjection.inject(f)
                                }
                            }
                        }, true)
            }
        }
    }
}