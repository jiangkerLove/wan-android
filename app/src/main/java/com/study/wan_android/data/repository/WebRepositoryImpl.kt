package com.study.wan_android.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.study.wan_android.data.model.ArticleModel
import com.study.wan_android.data.model.SystemGroup
import com.study.wan_android.data.web.WanAndroidService
import com.study.wan_android.ui.page.main.ArticlePageSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject

class WebRepositoryImpl @Inject constructor(
    private val service: WanAndroidService
) : DataRepository {

    override fun getArticleList(): Flow<PagingData<ArticleModel>> {
        return Pager(
            // 分页大小
            config = PagingConfig(20),
            pagingSourceFactory = { ArticlePageSource(service) }
        ).flow
    }

    override suspend fun getSystemGroupList(): List<SystemGroup> {
        return service.getSystemGroupList().data
    }


}