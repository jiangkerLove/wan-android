package com.study.wan_android.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.study.wan_android.data.model.ArticleModel
import com.study.wan_android.ui.page.main.ArticlePageSource
import kotlinx.coroutines.flow.Flow

interface DataRepository {

    fun getArticleList(): Flow<PagingData<ArticleModel>>

}