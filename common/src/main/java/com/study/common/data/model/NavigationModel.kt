package com.study.common.data.model

data class NavigationGroup(
    val name: String,
    val articles: List<NavigationModel>
)

data class NavigationModel(
    val title: String,
)