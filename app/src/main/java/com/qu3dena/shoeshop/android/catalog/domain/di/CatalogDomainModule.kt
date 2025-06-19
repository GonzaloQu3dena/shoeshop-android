package com.qu3dena.shoeshop.android.catalog.domain.di

import com.qu3dena.shoeshop.android.catalog.domain.repository.SneakerRepository
import com.qu3dena.shoeshop.android.catalog.domain.usecases.DeleteFavoriteUseCase
import com.qu3dena.shoeshop.android.catalog.domain.usecases.GetAllSneakersUseCase
import com.qu3dena.shoeshop.android.catalog.domain.usecases.GetFavoriteSneakersUseCase
import com.qu3dena.shoeshop.android.catalog.domain.usecases.GetSneakerByIdUseCase
import com.qu3dena.shoeshop.android.catalog.domain.usecases.SaveFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CatalogDomainModule {

    @Provides
    @Singleton
    fun provideGetAllSneakersUseCase(repository: SneakerRepository) =
        GetAllSneakersUseCase(repository)

    @Provides
    @Singleton
    fun provideGetSneakerByIdUseCase(repository: SneakerRepository) =
        GetSneakerByIdUseCase(repository)

    @Provides
    @Singleton
    fun provideSaveFavoriteUseCase(repository: SneakerRepository) =
        SaveFavoriteUseCase(repository)

    @Provides
    @Singleton
    fun provideDeleteFavoriteUseCase(repository: SneakerRepository) =
        DeleteFavoriteUseCase(repository)

    @Provides
    @Singleton
    fun provideGetFavoriteSneakersUseCase(repository: SneakerRepository) =
        GetFavoriteSneakersUseCase(repository)
}