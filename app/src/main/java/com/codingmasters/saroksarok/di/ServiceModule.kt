package com.codingmasters.saroksarok.di

import android.content.SharedPreferences
import com.codingmasters.saroksarok.data.service.BaseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {
    @Provides
    @Singleton
    fun provideService(
       retrofit: Retrofit
    ): BaseService =
        retrofit.create(BaseService::class.java)

    @Provides
    @Singleton
    fun provideHeaderIntercepter(autoLoginPrefeneces: SharedPreferences): HeaderInterceptor =
        HeaderInterceptor(autoLoginPrefeneces)

    @Singleton
    @Provides
    fun provideInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}