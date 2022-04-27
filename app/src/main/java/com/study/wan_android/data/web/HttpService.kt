package com.study.wan_android.data.web

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpService {

    private val retrofit = Retrofit.Builder()
        .baseUrl(WanAndroidService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getService(): WanAndroidService {
        return retrofit.create(WanAndroidService::class.java)
    }

}