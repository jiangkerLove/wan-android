package com.study.common.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.study.common.data.model.ArticleModel
import com.study.common.data.model.ArticlePageSource
import com.study.common.data.model.NavigationGroup
import com.study.common.data.model.SystemGroup
import com.study.common.data.web.WanAndroidService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WebRepositoryImpl @Inject constructor(
    private val service: WanAndroidService
) : DataRepository {

    override fun getArticleList(): Flow<PagingData<ArticleModel>> {
        return Pager(
            // 分页大小
            config = PagingConfig(20),
            pagingSourceFactory = {
                ArticlePageSource { page ->
                    service.getIndexList(page)
                }
            }
        ).flow
    }

    override fun getSystemGroupList(): Flow<List<SystemGroup>> {
        return flow { emit(service.getSystemGroupList().data) }
    }

    override fun getNavigationGroupList(): Flow<List<NavigationGroup>> {
        return flow { emit(service.getNavigationGroupList().data) }
    }

    override fun getPlazaArticleList(): Flow<PagingData<ArticleModel>> {
        return Pager(
            // 分页大小
            config = PagingConfig(20),
            pagingSourceFactory = {
                ArticlePageSource { page ->
                    service.getPlazaArticle(page, count = 20)
                }
            }
        ).flow
    }
}