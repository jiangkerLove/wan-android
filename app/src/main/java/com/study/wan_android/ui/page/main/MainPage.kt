package com.study.wan_android.ui.page.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.study.wan_android.ui.page.main.content.RecommendContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainPage(
    viewModel: MainPageModel = hiltViewModel(),
) {

    val pagerState = rememberPagerState()
    val state: LazyListState = rememberLazyListState()

    Scaffold(topBar = {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }
        ) {
            MainTab.tabs.forEachIndexed { index, tab ->
                Tab(
                    text = { Text(tab.tabName) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        CoroutineScope(Dispatchers.Main).launch {
                            pagerState.scrollToPage(index, pageOffset = 0F)
                        }
                    },
                )
            }
        }
    }
    ) {
        HorizontalPager(
            count = MainTab.tabs.size,
            state = pagerState,
            contentPadding = PaddingValues(top = it.calculateTopPadding())
        ) { index ->
            when (MainTab.tabs[index]) {
                MainTab.Recommend -> {
                    RecommendContent(viewModel, state)
                }
                MainTab.Plaza -> {
                    Text(text = "Plaza")
                }
                MainTab.Reply -> {
                    Text(text = "Reply")
                }
                MainTab.Term -> {
                    Text(text = "Term")
                }
            }
        }
    }
}