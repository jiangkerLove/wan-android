package com.study.xml_view.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.study.xml_view.R
import com.study.xml_view.databinding.FragmentMainBinding
import com.study.xml_view.ui.main.tab.*

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewPager.adapter = MainTabAdapter(this@MainFragment)

            tabLayout.tabMode = TabLayout.MODE_AUTO

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = getTabTitle(position)
            }.attach()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            RECOMMEND_PAGE_INDEX -> getString(R.string.tab_recommend)
            PLAZA_PAGE_INDEX -> getString(R.string.tab_plaza)
            TERM_PAGE_INDEX -> getString(R.string.tab_term)
            REPLY_PAGE_INDEX -> getString(R.string.tab_reply)
            else -> null
        }
    }
}