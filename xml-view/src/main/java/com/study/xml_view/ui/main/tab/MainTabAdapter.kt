package com.study.xml_view.ui.main.tab

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.study.xml_view.ui.main.fragment.PlazaFragment
import com.study.xml_view.ui.main.fragment.RecommendFragment
import com.study.xml_view.ui.main.fragment.ReplyFragment
import com.study.xml_view.ui.main.fragment.TermFragment

const val RECOMMEND_PAGE_INDEX = 0
const val PLAZA_PAGE_INDEX = 1
const val TERM_PAGE_INDEX = 2
const val REPLY_PAGE_INDEX = 3

class MainTabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        RECOMMEND_PAGE_INDEX to { RecommendFragment() },
        PLAZA_PAGE_INDEX to { PlazaFragment() },
        TERM_PAGE_INDEX to { TermFragment() },
        REPLY_PAGE_INDEX to { ReplyFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}