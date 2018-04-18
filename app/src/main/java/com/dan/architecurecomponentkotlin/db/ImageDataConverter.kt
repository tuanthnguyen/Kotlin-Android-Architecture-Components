package com.dan.architecurecomponentkotlin.db

import android.arch.persistence.room.TypeConverter
import com.dan.architecurecomponentkotlin.vo.ImageUrlsList
import com.dan.architecurecomponentkotlin.vo.User
import com.google.gson.Gson

class ImageDataConverter {

    @TypeConverter
    fun urlsToText(imageUrlsList: ImageUrlsList): String =
            Gson().toJson(imageUrlsList)

    @TypeConverter
    fun textToUrls(text: String): ImageUrlsList =
            Gson().fromJson(text, ImageUrlsList::class.java)

    @TypeConverter
    fun userToText(user: User): String =
            Gson().toJson(user)

    @TypeConverter
    fun textToUser(text: String): User =
            Gson().fromJson(text, User::class.java)
}