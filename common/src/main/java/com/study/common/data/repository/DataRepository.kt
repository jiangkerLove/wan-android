package com.study.common.data.repository

import androidx.paging.PagingData
import com.study.common.data.model.ArticleModel
import com.study.common.data.model.BannerModel
import com.study.common.data.model.NavigationGroup
import com.study.common.data.model.SystemGroup
import kotlinx.coroutines.flow.Flow

interface DataRepository {

    fun getArticleList(): Flow<PagingData<ArticleModel>>

    fun getBanners(): Flow<List<BannerModel>>

    fun getSystemGroupList(): Flow<List<SystemGroup>>

    fun getNavigationGroupList(): Flow<List<NavigationGroup>>

    fun getPlazaArticleList(): Flow<PagingData<ArticleModel>>
}