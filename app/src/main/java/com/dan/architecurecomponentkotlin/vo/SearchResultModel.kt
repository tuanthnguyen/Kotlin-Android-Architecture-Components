package com.dan.architecurecomponentkotlin.vo

import com.google.gson.annotations.SerializedName

data class SearchResultModel(val total: Int, @SerializedName("total_pages") val totalPages: Int,
                             val results: List<ImageModel>)