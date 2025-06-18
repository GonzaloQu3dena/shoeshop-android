package com.qu3dena.shoeshop.android.core.di

import com.qu3dena.shoeshop.android.catalog.data.source.remote.SneakerApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://sugary-wool-penguin.glitch.me/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideSneakerApiService(retrofit: Retrofit): SneakerApiService {
        return retrofit.create(SneakerApiService::class.java)
    }
}