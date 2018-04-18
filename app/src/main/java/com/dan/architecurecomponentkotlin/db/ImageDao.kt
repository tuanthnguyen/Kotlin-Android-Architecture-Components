package com.dan.architecurecomponentkotlin.db

import android.arch.persistence.room.*
import com.dan.architecurecomponentkotlin.vo.Image
import com.dan.architecurecomponentkotlin.vo.ImageModel
import io.reactivex.Single

@Dao
interface ImageDao {

    @Query("SELECT * FROM ${DatabaseConfig.IMAGE_TABLE_NAME}")
    fun getImages(): Single<List<ImageModel>>

    @Query("SELECT * FROM ${DatabaseConfig.IMAGE_TABLE_NAME} WHERE id = :imageId")
    fun getImage(imageId: String): Single<ImageModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImage(imageModel: ImageModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertImages(images: List<ImageModel>)

    @Update
    fun updateImage(imageModel: ImageModel)
}
