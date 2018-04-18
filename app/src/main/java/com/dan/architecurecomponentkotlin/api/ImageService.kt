package com.dan.architecurecomponentkotlin.api

import com.dan.architecurecomponentkotlin.vo.ImageModel
import com.dan.architecurecomponentkotlin.vo.SearchResultModel
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ImageService {

    @GET("photos")
    fun loadTrendingImages(@Header(ApiConfig.AUTH_HEADER) token: String,
                           @Query(ApiConfig.PAGE) pageNum: Int,
                           @Query(ApiConfig.PER_PAGE) pictureNumPerPage: Int,
                           @Query(ApiConfig.ORDER_BY) orderBy: String): Flowable<List<ImageModel>>

    @GET("search/photos")
    fun searchImages(@Header(ApiConfig.AUTH_HEADER) token: String,
                     @Query(ApiConfig.QUERY) keyword: String,
                     @Query(ApiConfig.PAGE) pageNum: Int,
                     @Query(ApiConfig.PER_PAGE) pictureNumPerPage: Int,
                     @Query(ApiConfig.ORDER_BY) orderBy: String): Single<SearchResultModel>
}