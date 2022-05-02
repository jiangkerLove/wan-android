package com.study.common.data.web

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HttpService {

    private val retrofit = Retrofit.Builder()
        .baseUrl(WanAndroidService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun getService(): WanAndroidService {
        return retrofit.create(WanAndroidService::class.java)
    }

}