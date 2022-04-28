package com.study.wan_android.ui.page.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.wan_android.data.model.ArticleModel
import com.study.wan_android.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPageModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            val list = repository.getArticleList()
            articleList.addAll(list)
        }
    }

    val articleList = mutableStateListOf<ArticleModel>()


}