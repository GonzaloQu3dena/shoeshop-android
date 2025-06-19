package com.qu3dena.shoeshop.android.catalog.presentation.navigation

const val DETAIL_ARGUMENT_SNEAKER_ID = "sneakerId"

sealed class Screen(val route: String) {

    object SneakerList : Screen("sneaker_list")

    object SneakerDetail : Screen("sneaker_detail/{$DETAIL_ARGUMENT_SNEAKER_ID}") {
        fun passSneakerId(sneakerId: Long): String {
            return this.route
                .replace(
                    oldValue = "{$DETAIL_ARGUMENT_SNEAKER_ID}",
                    newValue = sneakerId.toString()
                )
        }
    }

    object FavoriteSneakersList : Screen("favorite_sneakers_list")
}