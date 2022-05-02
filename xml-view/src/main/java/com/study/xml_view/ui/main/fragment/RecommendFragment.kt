package com.study.xml_view.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.study.xml_view.databinding.FragmentRecommendBinding
import com.study.xml_view.ui.main.adapter.ArticleAdapter
import com.study.xml_view.ui.main.adapter.FooterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecommendFragment : Fragment() {

    private var _binding: FragmentRecommendBinding? = null

    private val binding get() = _binding!!

    private val articleAdapter = ArticleAdapter()

    private val viewModel by viewModels<RecommendViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecommendBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            recyclerView.adapter =
                articleAdapter.withLoadStateFooter(FooterAdapter { articleAdapter.retry() })
            lifecycleScope.launch {
                viewModel.getArticleList().collectLatest { pagingData ->
                    articleAdapter.submitData(pagingData)
                }
            }
            articleAdapter.addLoadStateListener {
                when (it.refresh) {
                    is LoadState.NotLoading -> {
                        progressBar.visibility = View.INVISIBLE
                        recyclerView.visibility = View.VISIBLE
                    }
                    is LoadState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.INVISIBLE
                    }
                    else -> {
                        val state = it.refresh as LoadState.Error
                        progressBar.visibility = View.INVISIBLE
                        Toast.makeText(
                            requireContext(),
                            "Load Error: ${state.error.message}",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}