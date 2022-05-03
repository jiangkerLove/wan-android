package com.study.xml_view.ui.classify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.study.xml_view.R
import com.study.xml_view.databinding.FragmentClassifyBinding
import com.study.xml_view.ui.classify.tab.ClassifyTabAdapter
import com.study.xml_view.ui.classify.tab.NAVIGATION_PAGE_INDEX
import com.study.xml_view.ui.classify.tab.OFFICIAL_PAGE_INDEX
import com.study.xml_view.ui.classify.tab.SYSTEM_PAGE_INDEX

class ClassifyFragment : Fragment() {

    private var _binding: FragmentClassifyBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClassifyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewPager.adapter = ClassifyTabAdapter(this@ClassifyFragment)

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
            SYSTEM_PAGE_INDEX -> getString(R.string.tab_system)
            NAVIGATION_PAGE_INDEX -> getString(R.string.tab_navigation)
            OFFICIAL_PAGE_INDEX -> getString(R.string.tab_official)
            else -> null
        }
    }
}