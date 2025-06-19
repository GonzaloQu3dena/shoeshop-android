package com.qu3dena.shoeshop.android.catalog.domain.repository

import com.qu3dena.shoeshop.android.catalog.domain.model.Sneaker
import kotlinx.coroutines.flow.Flow

interface SneakerRepository {

    fun getAll(): Flow<List<Sneaker>>

    fun getById(id: Long): Flow<Sneaker>

    fun deleteFavorite(sneaker: Sneaker): Flow<Unit>

    fun saveFavorite(sneaker: Sneaker): Flow<Sneaker>

    fun getFavorites(): Flow<List<Sneaker>>
}