package com.study.xml_view.ui.main.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.study.xml_view.ui.base.ListFragment
import com.study.xml_view.ui.main.adapter.ArticleAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecommendFragment : ListFragment<ArticleAdapter>() {

    private val viewModel by viewModels<RecommendViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.getArticleList().collectLatest { pagingData ->
                mAdapter.submitData(pagingData)
            }
        }
    }

    override fun onCreateAdapter() = ArticleAdapter()
}