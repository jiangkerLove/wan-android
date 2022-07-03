package com.study.common.data.model

/*
{
    "desc": "我们支持订阅啦~",
    "id": 30,
    "imagePath": "https://www.wanandroid.com/blogimgs/42da12d8-de56-4439-b40c-eab66c227a4b.png",
    "isVisible": 1,
    "order": 2,
    "title": "我们支持订阅啦~", 
    "type": 0,
    "url": "https://www.wanandroid.com/blog/show/3352"
}
 */
data class BannerModel(
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val type: Int,
    val url: String
)
