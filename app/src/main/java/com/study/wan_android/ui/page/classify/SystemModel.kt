package com.study.wan_android.ui.page.classify

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.wan_android.data.model.SystemGroup
import com.study.wan_android.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SystemModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    val systemGroupList = mutableStateOf(emptyList<SystemGroup>())

    init {
        viewModelScope.launch {
            systemGroupList.value = repository.getSystemGroupList()
        }

    }

}