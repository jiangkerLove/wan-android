package com.study.xml_view.ui.classify.tab

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.study.xml_view.ui.classify.fragment.NavigationFragment
import com.study.xml_view.ui.classify.fragment.OfficialFragment
import com.study.xml_view.ui.classify.fragment.SystemFragment

const val SYSTEM_PAGE_INDEX = 0
const val NAVIGATION_PAGE_INDEX = 1
const val OFFICIAL_PAGE_INDEX = 2

class ClassifyTabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        SYSTEM_PAGE_INDEX to { SystemFragment() },
        NAVIGATION_PAGE_INDEX to { NavigationFragment() },
        OFFICIAL_PAGE_INDEX to { OfficialFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}