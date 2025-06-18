package com.qu3dena.shoeshop.android.catalog.domain.usecases

import com.qu3dena.shoeshop.android.catalog.domain.model.Sneaker
import com.qu3dena.shoeshop.android.catalog.domain.repository.SneakerRepository
import kotlinx.coroutines.flow.Flow

class GetSneakerByIdUseCase(
    private val repository: SneakerRepository
) {
    fun invoke(id: Long): Flow<Sneaker> = repository.getById(id)
}