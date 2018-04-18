package com.dan.architecurecomponentkotlin.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.dan.architecurecomponentkotlin.vo.ImageModel

@Database(entities = [ImageModel::class], version = 1)
abstract class ImageDb : RoomDatabase() {

    abstract fun imageDao(): ImageDao
}