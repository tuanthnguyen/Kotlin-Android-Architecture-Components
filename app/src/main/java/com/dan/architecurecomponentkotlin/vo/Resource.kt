package com.dan.architecurecomponentkotlin.vo

data class Resource<out T>(val data: T? = null, val status: Status = Status.LOADING, val errorMessage: String = "") {

    companion object {
        fun <T> success(data: T): Resource<T> = Resource(data, Status.SUCCESS)
        fun <T> failure(errorMessage: String): Resource<T> = Resource(null, Status.ERROR, errorMessage)
        fun <T> loading(): Resource<T> = Resource(null, Status.LOADING)
    }
}

enum class Status {
    SUCCESS, ERROR, LOADING
}