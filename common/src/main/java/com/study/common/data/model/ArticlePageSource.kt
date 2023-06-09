package com.study.common.data.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.study.common.data.web.ResponseModel
import com.study.common.data.web.WanAndroidService

class ArticleWithBannerSource(
    private val service: WanAndroidService
) : PagingSource<Int, Any>() {

    override fun getRefreshKey(state: PagingState<Int, Any>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Any> {
        return try {
            // key 代表当前页数
            val page = params.key ?: 0
            val resultList = mutableListOf<Any>()
            if (page == 0) {
                val banner = service.getBanner()
                resultList.add(banner.data)
            }
            val reposeData = service.getIndexList(page)
            val articleList = reposeData.data.dataList
            resultList.addAll(articleList)
            val prevKey = if (page > 0) page - 1 else null
            val nextKey = if (articleList.isNotEmpty()) page + 1 else null
            LoadResult.Page(resultList, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

class ArticlePageSource(
    private val queryDataSource: suspend (page: Int) -> ResponseModel<ListWrapperModel<ArticleModel>>
) : PagingSource<Int, ArticleModel>() {

    override fun getRefreshKey(state: PagingState<Int, ArticleModel>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleModel> {
        return try {
            // key 代表当前页数
            val page = params.key ?: 1
            val reposeData = queryDataSource(page)
            val articleList = reposeData.data.dataList
            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (articleList.isNotEmpty()) page + 1 else null
            LoadResult.Page(articleList, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}