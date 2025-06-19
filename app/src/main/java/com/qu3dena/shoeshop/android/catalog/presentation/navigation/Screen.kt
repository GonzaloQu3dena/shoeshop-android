package com.qu3dena.shoeshop.android.catalog.presentation.navigation

sealed class Screen(val route: String) {
    object SneakerList : Screen("sneaker_list")
    object FavoriteSneakersList : Screen("favorite_sneakers_list")
}