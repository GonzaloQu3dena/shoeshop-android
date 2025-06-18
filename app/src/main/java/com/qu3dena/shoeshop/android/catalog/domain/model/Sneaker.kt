package com.qu3dena.shoeshop.android.catalog.domain.model

data class Sneaker(
    val id: Long,
    val name: String,
    val brand: String,
    val gender: String,
    val category: String,
    val price: Long,
    val isFavorite: Boolean,

    val sizesAvailable: List<SizesAvailable>,

    val image: String,
    val rating: Double,
    val reviewCount: Long,
    val description: String,
    val availableColors: List<String>
)

data class SizesAvailable(
    val size: Double,
    val quantity: Long
)