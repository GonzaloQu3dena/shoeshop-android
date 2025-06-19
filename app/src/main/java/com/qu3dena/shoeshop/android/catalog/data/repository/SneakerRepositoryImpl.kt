package com.qu3dena.shoeshop.android.catalog.data.repository

import android.util.Log
import com.qu3dena.shoeshop.android.catalog.data.source.local.SneakerDao
import com.qu3dena.shoeshop.android.catalog.data.source.local.model.FavoriteSneakerEntity
import com.qu3dena.shoeshop.android.catalog.data.source.remote.SneakerApiService
import com.qu3dena.shoeshop.android.catalog.domain.model.Sneaker
import com.qu3dena.shoeshop.android.catalog.domain.repository.SneakerRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Singleton

@Singleton
class SneakerRepositoryImpl @Inject constructor(
    private val apiService: SneakerApiService,
    private val dao: SneakerDao
) : SneakerRepository {

    override fun getAll(): Flow<List<Sneaker>> = flow {
        val sneakers = apiService.getSneakers().map { it ->
            it.toDomain()
        }

        val favoritesIds = dao.getAllFavorites().first()
            .map { it.id }.toSet()

        Log.d("SneakerRepository", "Favorites IDs: $favoritesIds")

        emit(
            sneakers.map { sneaker ->
                sneaker.copy(isFavorite = favoritesIds.contains(sneaker.id))
            }
        )
    }.flowOn(Dispatchers.IO)

    override fun getById(id: Long): Flow<Sneaker> = flow {
        try {
            val sneaker = apiService.getSneakers().find { it.id == id }
                ?: throw NoSuchElementException("Sneaker with id $id not found")

            emit(sneaker.toDomain())
        } catch (ex: Exception) {
            throw ex
        }
    }.flowOn(Dispatchers.IO)

    override fun deleteFavorite(sneaker: Sneaker): Flow<Unit> = flow {
        dao.delete(
            FavoriteSneakerEntity(
                id = sneaker.id,
                name = sneaker.name,
                isFavorite = sneaker.isFavorite,
                image = sneaker.image,
                description = sneaker.description
            )
        )
        emit(Unit)
    }.flowOn(Dispatchers.IO)

    override fun saveFavorite(sneaker: Sneaker): Flow<Sneaker> = flow {
        dao.insert(FavoriteSneakerEntity.fromDomain(sneaker))
        emit(sneaker.copy(isFavorite = true))
    }.flowOn(Dispatchers.IO)

    override fun getFavorites(): Flow<List<Sneaker>> = flow {

        try {
            var favorites = dao.getAllFavorites().first()
                .map { it.toDomain() }

            emit(favorites)
        } catch (ex: Exception) {
            throw ex
        }
    }.flowOn(Dispatchers.IO)
}