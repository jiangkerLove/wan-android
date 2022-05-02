package com.study.common.data.repository

import androidx.paging.PagingData
import com.study.common.data.model.ArticleModel
import com.study.common.data.model.SystemGroup
import kotlinx.coroutines.flow.Flow

interface DataRepository {

    fun getArticleList(): Flow<PagingData<ArticleModel>>

    suspend fun getSystemGroupList(): List<SystemGroup>
}