package com.study.wan_android.data.repository

import androidx.paging.PagingData
import com.study.wan_android.data.model.ArticleModel
import com.study.wan_android.data.model.SystemGroup
import kotlinx.coroutines.flow.Flow

interface DataRepository {

    fun getArticleList(): Flow<PagingData<ArticleModel>>

    suspend fun getSystemGroupList(): List<SystemGroup>
}