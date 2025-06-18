package com.qu3dena.shoeshop.android.catalog.data.entities

import com.google.gson.annotations.SerializedName
import com.qu3dena.shoeshop.android.catalog.domain.model.SizesAvailable
import com.qu3dena.shoeshop.android.catalog.domain.model.Sneaker

data class SneakerDto(
    val id: Long,
    val name: String,
    val brand: String,
    val gender: String,
    val category: String,
    val price: Long,

    @SerializedName("sizes_available")
    val sizesAvailable: List<SizesAvailable>,

    val image: String,
    val rating: Double,

    @SerializedName("review_count")
    val reviewCount: Long,

    val description: String,

    @SerializedName("available_colors")
    val availableColors: List<String>
) {
    fun toDomain(): Sneaker {
        return Sneaker(
            id = id,
            name = name,
            brand = brand,
            gender = gender,
            category = category,
            price = price,
            sizesAvailable = sizesAvailable,
            image = image,
            rating = rating,
            reviewCount = reviewCount,
            description = description,
            availableColors = availableColors,
            isFavorite = false
        )
    }
}