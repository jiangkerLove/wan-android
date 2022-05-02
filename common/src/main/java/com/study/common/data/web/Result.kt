package com.study.common.data.web

import com.google.gson.annotations.SerializedName

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

data class ResponseModel<T>(
    val data: T,
    @SerializedName("errorCode")
    val code: Int,
    @SerializedName("errorMsg")
    val message: String,
)