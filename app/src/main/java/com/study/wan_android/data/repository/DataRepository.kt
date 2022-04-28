package com.study.wan_android.data.repository

import com.study.wan_android.data.model.ArticleModel

interface DataRepository {

    suspend fun getArticleList(): List<ArticleModel>

}