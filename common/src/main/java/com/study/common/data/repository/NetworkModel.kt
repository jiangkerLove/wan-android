package com.study.common.data.repository

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