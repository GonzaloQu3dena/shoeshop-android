package com.qu3dena.shoeshop.android.catalog.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.qu3dena.shoeshop.android.catalog.domain.model.SizesAvailable
import com.qu3dena.shoeshop.android.catalog.domain.model.Sneaker

@Entity(tableName = "favorite_sneakers")
data class FavoriteSneakerEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val isFavorite: Boolean = false
) {
    companion object {
        fun fromDomain(sneaker: Sneaker): FavoriteSneakerEntity {
            return FavoriteSneakerEntity(
                id = sneaker.id,
                name = sneaker.name,
                isFavorite = sneaker.isFavorite
            )
        }
    }

    fun toDomain(): Sneaker {
        return Sneaker(
            id = id,
            name = name,
            brand = "",
            gender = "",
            category = "",
            price = 0,
            isFavorite = isFavorite,
            sizesAvailable = emptyList<SizesAvailable>(),
            image = "",
            rating = 0.0,
            reviewCount = 0,
            description = "",
            availableColors = emptyList()
        )
    }
}