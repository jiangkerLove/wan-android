package com.study.xml_view.ui.classify.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.study.xml_view.databinding.FragmentRecyclerViewBinding
import com.study.xml_view.ui.activity.WebViewActivity
import com.study.xml_view.ui.classify.adapter.NavigationAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NavigationFragment : Fragment() {

    private var _binding: FragmentRecyclerViewBinding? = null

    private val binding get() = _binding!!

    private val navigationAdapter = NavigationAdapter {
        WebViewActivity.jumpToActivity(requireActivity(), it.title, it.link)
    }

    private val viewModel by viewModels<NavigationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            recyclerView.adapter = navigationAdapter
            lifecycleScope.launch {
                viewModel.navigationGroupList.collectLatest { list ->
                    navigationAdapter.update(list)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}