package com.dan.architecurecomponentkotlin.vo

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.dan.architecurecomponentkotlin.db.DatabaseConfig
import com.dan.architecurecomponentkotlin.db.ImageDataConverter
import com.google.gson.annotations.Expose

@Entity(tableName = DatabaseConfig.IMAGE_TABLE_NAME)
@TypeConverters(ImageDataConverter::class)
data class ImageModel(
        @PrimaryKey val id: String,
        @ColumnInfo(name = "urls") val urls: ImageUrlsList,
        @ColumnInfo(name = "user") val user: User,
        @Expose(serialize = false, deserialize = false)
        @ColumnInfo(name = "downloaded_file_path") var downloadedFilePath: String?)

data class ImageUrlsList(val raw: String, val full: String, val regular: String, val small: String,
                         val thumb: String)

data class User(val id: String, val username: String, val name: String)