package com.study.wan_android.data.model

import com.google.gson.annotations.SerializedName

data class ListWrapperModel<T>(
    val curPage: Int,
    @SerializedName("datas")
    val dataList: List<T>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int,
)