package com.qu3dena.shoeshop.android.catalog.data.repository

import com.qu3dena.shoeshop.android.catalog.data.source.remote.SneakerApiService
import com.qu3dena.shoeshop.android.catalog.domain.model.Sneaker
import com.qu3dena.shoeshop.android.catalog.domain.repository.SneakerRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Singleton

@Singleton
class SneakerRepositoryImpl @Inject constructor(
    private val apiService: SneakerApiService
) : SneakerRepository {

    override fun getAll(): Flow<List<Sneaker>> = flow {
        try {
            val sneakers = apiService.getSneakers().map { it ->
                it.toDomain()
            }
            emit(sneakers)
        }
        catch (ex: Exception) {
            throw ex
        }
    }.flowOn(Dispatchers.IO)

    override fun getById(id: Long): Flow<Sneaker> = flow {
        try {
            val sneaker = apiService.getSneakers().find { it.id == id }
                ?: throw NoSuchElementException("Sneaker with id $id not found")

            emit(sneaker.toDomain())
        }
        catch (ex: Exception) {
            throw ex
        }
    }.flowOn(Dispatchers.IO)

    override fun deleteFavorite(sneaker: Sneaker): Flow<Unit> {
        TODO("Not yet implemented")
    }

    override fun saveFavorite(sneaker: Sneaker): Flow<Sneaker> {
        TODO("Not yet implemented")
    }

}