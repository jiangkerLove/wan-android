package com.study.xml_view.ui.main.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.study.xml_view.ui.base.ListFragment
import com.study.xml_view.ui.main.adapter.PlazaArticleAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

// 广场tab
@AndroidEntryPoint
class PlazaFragment : ListFragment<PlazaArticleAdapter>() {

    private val viewModel by viewModels<PlazaViewModel>()

    override fun onCreateAdapter() = PlazaArticleAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.getPlazaArticleList().collectLatest { pagingData ->
                mAdapter.submitData(pagingData)
            }
        }
    }
}