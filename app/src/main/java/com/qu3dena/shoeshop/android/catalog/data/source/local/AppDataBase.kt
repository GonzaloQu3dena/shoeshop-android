package com.qu3dena.shoeshop.android.catalog.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.qu3dena.shoeshop.android.catalog.data.source.local.model.FavoriteSneakerEntity

@Database(
    entities = [FavoriteSneakerEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun sneakerDao(): SneakerDao
}