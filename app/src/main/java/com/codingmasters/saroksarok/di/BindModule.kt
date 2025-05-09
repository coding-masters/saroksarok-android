package com.codingmasters.saroksarok.di

import com.codingmasters.saroksarok.data.datasource.BaseDataSource
import com.codingmasters.saroksarok.data.datasource_impl.BaseDataSourceImpl
import com.codingmasters.saroksarok.data.repository_impl.BaseRepositoryImpl
import com.codingmasters.saroksarok.domain.repository.BaseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {
    @Binds
    @Singleton
    abstract fun bindBaseRepository(baseRepositoryImpl: BaseRepositoryImpl): BaseRepository

    @Binds
    @Singleton
    abstract fun provideBaseDataSource(baseDataSourceImpl: BaseDataSourceImpl): BaseDataSource
}