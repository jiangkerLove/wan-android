package com.study.common.data.model

import com.google.gson.annotations.SerializedName

data class SystemGroup(
    val name: String,
    @SerializedName("children")
    val childes: List<SystemChild>
)

data class SystemChild(
    val name: String,
    val id: Int
)