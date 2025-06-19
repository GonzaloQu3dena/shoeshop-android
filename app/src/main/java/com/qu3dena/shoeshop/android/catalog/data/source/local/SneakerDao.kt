package com.qu3dena.shoeshop.android.catalog.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.qu3dena.shoeshop.android.catalog.data.source.local.model.FavoriteSneakerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SneakerDao {

    @Query("SELECT * FROM favorite_sneakers")
    fun getAllFavorites(): Flow<List<FavoriteSneakerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sneaker: FavoriteSneakerEntity)

    @Delete
    fun delete(sneaker: FavoriteSneakerEntity)
}