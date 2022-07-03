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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.study.common.data.model.BannerModel
import com.study.xml_view.databinding.FragmentRecommendBinding
import com.study.xml_view.ui.main.adapter.ArticleAdapter
import com.study.xml_view.ui.main.adapter.FooterAdapter
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecommendFragment : Fragment() {

    private val viewModel by viewModels<RecommendViewModel>()
    private var _binding: FragmentRecommendBinding? = null

    private  val mAdapter = ArticleAdapter()

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
            swipeRefresh.setOnRefreshListener {
                mAdapter.refresh()
            }
            recyclerView.adapter =
                mAdapter.withLoadStateFooter(FooterAdapter { mAdapter.retry() })
            mAdapter.addLoadStateListener {
                when (it.refresh) {
                    is LoadState.NotLoading -> {
                        progressBar.visibility = View.INVISIBLE
                        recyclerView.visibility = View.VISIBLE
//                        swipeRefresh.isRefreshing = false
                    }
                    is LoadState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.INVISIBLE
                    }
                    else -> {
                        val state = it.refresh as LoadState.Error
                        progressBar.visibility = View.INVISIBLE
//                        swipeRefresh.isRefreshing = false
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
                launch {
                    viewModel.getBanners().collectLatest { list ->
                        banner.setAdapter(object : BannerImageAdapter<BannerModel?>(list) {
                            override fun onBindView(
                                holder: BannerImageHolder?,
                                data: BannerModel?,
                                position: Int,
                                size: Int
                            ) {
                                //图片加载自己实现
                                Glide.with(holder!!.itemView)
                                    .load(data!!.imagePath)
                                    .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                                    .into(holder.imageView)
                            }
                        })
                            .addBannerLifecycleObserver(this@RecommendFragment) //添加生命周期观察者
                            .setIndicator(CircleIndicator(requireContext()))
                    }
                }

            }


        }

    }

}