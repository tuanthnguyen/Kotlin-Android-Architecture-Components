package com.dan.architecurecomponentkotlin.repository.mapper

import com.dan.architecurecomponentkotlin.vo.Image
import com.dan.architecurecomponentkotlin.vo.ImageModel

class ImageMapperImpl: ImageMapper {
    override fun dataToDomain(imageModel: ImageModel): Image {
        return Image(imageModel.id, imageModel.urls.small, imageModel.urls.full, imageModel.user.name, imageModel.downloadedFilePath)
    }
}