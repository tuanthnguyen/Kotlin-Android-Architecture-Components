package com.dan.architecurecomponentkotlin.di

import android.app.Application
import android.arch.persistence.room.Room
import com.dan.architecurecomponentkotlin.api.ApiConfig
import com.dan.architecurecomponentkotlin.api.ImageService
import com.dan.architecurecomponentkotlin.db.DatabaseConfig
import com.dan.architecurecomponentkotlin.db.ImageDao
import com.dan.architecurecomponentkotlin.db.ImageDb
import com.dan.architecurecomponentkotlin.repository.ImageRepository
import com.dan.architecurecomponentkotlin.repository.ImageRepositoryImpl
import com.dan.architecurecomponentkotlin.repository.mapper.ImageMapper
import com.dan.architecurecomponentkotlin.repository.mapper.ImageMapperImpl
import com.dan.architecurecomponentkotlin.util.rx.AppSchedulerProvider
import com.dan.architecurecomponentkotlin.util.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/*
 * Created by thanhtuan on 4/11/18.
 */
@Module(includes = [(ViewModelModule::class)])
class AppModule {
    @Singleton @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()
    }

    @Singleton @Provides
    fun provideGithubService(okHttpClient: OkHttpClient): ImageService =
            Retrofit.Builder()
                    .baseUrl(ApiConfig.IMAGE_BASE_HOST)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(ImageService::class.java)


    @Singleton @Provides
    fun provideDb(app: Application): ImageDb =
            Room.databaseBuilder(app, ImageDb::class.java, DatabaseConfig.DATABASE_NAME).build()


    @Singleton @Provides
    fun provideImageDao(db: ImageDb): ImageDao =
            db.imageDao()

    @Singleton @Provides
    fun provideImageRepository(imageRepositoryImpl: ImageRepositoryImpl): ImageRepository = imageRepositoryImpl

    @Singleton @Provides
    fun provideImageMapper(): ImageMapper = ImageMapperImpl()

    @Singleton @Provides
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()
}
