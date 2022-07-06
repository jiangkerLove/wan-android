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
import com.study.xml_view.ui.main.adapter.FooterAdapter
import com.study.xml_view.ui.main.adapter.RecommendAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecommendFragment : Fragment() {

    private val viewModel by viewModels<RecommendViewModel>()
    private var _binding: FragmentRecommendBinding? = null

    private val mAdapter = RecommendAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentRecommendBinding.inflate(inflater, container, false)
        _binding = binding
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.apply {
            recyclerView.adapter =
                mAdapter.withLoadStateFooter(FooterAdapter { mAdapter.retry() })
            mAdapter.addLoadStateListener {
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
            lifecycleScope.launch {
                launch {
                    viewModel.getArticleList().collectLatest { pagingData ->
                        mAdapter.submitData(pagingData)
                    }
                }

            }


        }

    }

}