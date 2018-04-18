package com.dan.architecurecomponentkotlin.repository.mapper

import com.dan.architecurecomponentkotlin.vo.Image
import com.dan.architecurecomponentkotlin.vo.ImageModel

interface ImageMapper {

    fun dataToDomain(imageModel: ImageModel): Image
}