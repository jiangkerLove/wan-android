package com.study.xml_view.ui.classify

import androidx.lifecycle.ViewModel
import com.study.common.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClassifyViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    val systemGroupList = repository.getSystemGroupList()

}