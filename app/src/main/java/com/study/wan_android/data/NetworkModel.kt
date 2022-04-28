package com.study.wan_android.data

import com.study.wan_android.data.repository.DataRepository
import com.study.wan_android.data.repository.WebRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModel {

    @Binds
    @Singleton
    abstract fun provideRepository(repository: WebRepositoryImpl): DataRepository

}