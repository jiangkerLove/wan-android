package com.study.common.data.web

import com.study.common.data.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WanAndroidService {
    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
    }

    //首页
    @GET("/article/list/{page}/json")
    suspend fun getIndexList(@Path("page") page: Int): ResponseModel<ListWrapperModel<ArticleModel>>

    //banner
    @GET("/banner/json")
    suspend fun getBanner(): ResponseModel<List<BannerModel>>

    @GET("/user_article/list/{page}/json")
    suspend fun getPlazaArticle(
        @Path("page") page: Int,
        @Query("page_size") count: Int = 10
    ): ResponseModel<ListWrapperModel<ArticleModel>>

    //体系
    @GET("/tree/json")
    suspend fun getSystemGroupList(): ResponseModel<List<SystemGroup>>

    //体系
    @GET("/navi/json")
    suspend fun getNavigationGroupList(): ResponseModel<List<NavigationGroup>>
}

