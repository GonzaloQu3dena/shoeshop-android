package com.qu3dena.shoeshop.android.catalog.domain.usecases

import com.qu3dena.shoeshop.android.catalog.domain.model.Sneaker
import com.qu3dena.shoeshop.android.catalog.domain.repository.SneakerRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteSneakersUseCase(
    private val repository: SneakerRepository
) {
    fun invoke(): Flow<List<Sneaker>> = repository.getFavorites()
}