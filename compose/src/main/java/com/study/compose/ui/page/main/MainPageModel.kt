package com.study.compose.ui.page.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.study.common.data.model.ArticleModel
import com.study.common.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainPageModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    val articleList: Flow<PagingData<ArticleModel>>
        get() {
            return repository.getArticleList().cachedIn(viewModelScope)
        }


}