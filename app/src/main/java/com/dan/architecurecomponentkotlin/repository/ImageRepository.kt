package com.dan.architecurecomponentkotlin.repository

import com.dan.architecurecomponentkotlin.vo.Image
import io.reactivex.Flowable

interface ImageRepository {
    fun loadImages(): Flowable<List<Image>>
}