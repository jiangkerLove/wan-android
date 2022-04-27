package com.study.wan_android.data.model

data class ListWrapperModel<T> (
    private val curPage: Int,
    private val datas: List<T>,
    private val offset: Int,
    private val over: Boolean,
    private val pageCount: Int,
    private val size: Int,
    private val total: Int,
)