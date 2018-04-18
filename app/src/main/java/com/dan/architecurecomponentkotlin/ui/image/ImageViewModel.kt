package com.dan.architecurecomponentkotlin.ui.image

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.dan.architecurecomponentkotlin.repository.ImageRepository
import com.dan.architecurecomponentkotlin.util.ext.toLiveData
import com.dan.architecurecomponentkotlin.util.rx.SchedulerProvider
import com.dan.architecurecomponentkotlin.vo.Image
import com.dan.architecurecomponentkotlin.vo.Resource
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ImageViewModel @Inject constructor(
        imageRepository: ImageRepository,
        schedulerProvider: SchedulerProvider
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    var images: LiveData<Resource<List<Image>>>

    init {
        images = imageRepository.loadImages()
                .map { Resource.success(it) }
                .onErrorReturn { e ->
                    Resource.failure(e.message ?: "unknown")
                }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
//                .onErrorResumeNext(Flowable.empty())
                .toLiveData()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}