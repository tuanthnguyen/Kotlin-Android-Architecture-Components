package com.dan.architecurecomponentkotlin.repository

import com.dan.architecurecomponentkotlin.api.ApiConfig
import com.dan.architecurecomponentkotlin.api.ApiConfig.token
import com.dan.architecurecomponentkotlin.api.ImageService
import com.dan.architecurecomponentkotlin.db.ImageDao
import com.dan.architecurecomponentkotlin.repository.mapper.ImageMapper
import com.dan.architecurecomponentkotlin.vo.Image
import io.reactivex.Flowable
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(private val imageDao: ImageDao,
                                              private val imageService: ImageService,
                                              private val imageMapper: ImageMapper) : ImageRepository {

    override fun loadImages(): Flowable<List<Image>> {
        return imageService.loadTrendingImages(token, ApiConfig.DEFAULT_PAGE,
                ApiConfig.DEFAULT_PER_PAGE, ApiConfig.DEFAULT_ORDER_BY)
                .doOnNext { imageDao.insertImages(it) }
                .flatMap { Flowable.fromIterable(it) }
                .map { imageMapper.dataToDomain(it) }
                .toList()
                .toFlowable()
    }
}