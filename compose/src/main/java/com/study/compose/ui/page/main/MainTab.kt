package com.study.compose.ui.page.main

sealed class MainTab(
    val tabName: String
) {

    object Recommend : MainTab("推荐")
    object Plaza : MainTab("广场")
    object Term : MainTab("项目")
    object Reply : MainTab("回答")

    companion object {
        val tabs = arrayOf(Recommend, Plaza, Term, Reply)
    }

}