package com.study.wan_android.data.repository

import com.study.wan_android.data.model.ArticleModel
import com.study.wan_android.data.web.WanAndroidService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WebRepositoryImpl @Inject constructor(
    private val service: WanAndroidService
) : DataRepository {

    override suspend fun getArticleList(): List<ArticleModel> {
        return withContext(Dispatchers.IO){
            service.getIndexList(1).data.dataList
        }
    }
}