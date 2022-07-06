package com.study.xml_view.ui.main.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.study.common.data.model.BannerModel
import com.study.common.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RecommendViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    fun getArticleList(): Flow<PagingData<Any>> {
        return repository.getArticleList().cachedIn(viewModelScope)
    }

    fun getBanners(): Flow<List<BannerModel>> {
        return repository.getBanners()
    }

}