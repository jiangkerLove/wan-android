package com.study.compose

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.study.compose.R

sealed class Route(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val drawableId: Int
) {

    object Main : Route("main", R.string.tab_main, R.drawable.ic_mian)
    object Classify : Route("classify", R.string.tab_classify, R.drawable.ic_classify)
    object Mine : Route("mine", R.string.tab_mine, R.drawable.ic_mine)


    companion object {
        val routes = listOf(
            Main,
            Classify,
            Mine
        )
    }
}