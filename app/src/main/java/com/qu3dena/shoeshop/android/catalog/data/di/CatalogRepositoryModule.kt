package com.qu3dena.shoeshop.android.catalog.data.di

import com.qu3dena.shoeshop.android.catalog.data.repository.SneakerRepositoryImpl
import com.qu3dena.shoeshop.android.catalog.domain.repository.SneakerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CatalogRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSneakerRepository(
        impl: SneakerRepositoryImpl
    ) : SneakerRepository
}