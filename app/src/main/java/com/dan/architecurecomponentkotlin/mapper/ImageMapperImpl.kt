package com.dan.architecurecomponentkotlin.mapper

import com.dan.architecurecomponentkotlin.vo.Image
import com.dan.architecurecomponentkotlin.vo.ImageModel

class ImageMapperImpl : ImageMapper {

    override fun dataToDomain(imageModel: ImageModel): Image =
            Image(imageModel.id, imageModel.urls.small, imageModel.urls.full, imageModel.user.name,
                    imageModel.downloadedFilePath)
}