package com.study.common.data.web

import com.study.common.data.model.ArticleModel
import com.study.common.data.model.ListWrapperModel
import com.study.common.data.model.SystemGroup
import retrofit2.http.GET
import retrofit2.http.Path

interface WanAndroidService {
    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
    }

    //首页
    @GET("/article/list/{page}/json")
    suspend fun getIndexList(@Path("page") page: Int): ResponseModel<ListWrapperModel<ArticleModel>>

    //体系
    @GET("/tree/json")
    suspend fun getSystemGroupList(): ResponseModel<List<SystemGroup>>
}

