package com.dan.architecurecomponentkotlin.mapper

import com.dan.architecurecomponentkotlin.vo.Image
import com.dan.architecurecomponentkotlin.vo.ImageModel

interface ImageMapper {

    fun dataToDomain(imageModel: ImageModel): Image
}