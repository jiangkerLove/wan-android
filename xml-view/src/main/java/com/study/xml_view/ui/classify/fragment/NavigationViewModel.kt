package com.study.xml_view.ui.classify.fragment

import androidx.lifecycle.ViewModel
import com.study.common.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    val navigationGroupList = repository.getNavigationGroupList()

}