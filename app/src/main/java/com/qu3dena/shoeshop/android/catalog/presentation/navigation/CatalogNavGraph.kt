package com.qu3dena.shoeshop.android.catalog.presentation.navigation

import androidx.navigation.navigation
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.qu3dena.shoeshop.android.catalog.presentation.ui.screen.FavoriteSneakersListView
import com.qu3dena.shoeshop.android.catalog.presentation.ui.screen.SneakerDetailView
import com.qu3dena.shoeshop.android.catalog.presentation.ui.screen.SneakerListView

fun NavGraphBuilder.catalogNavGraph(
    route: String,
    navController: NavHostController
) {
    navigation(
        route = route,
        startDestination = Screen.SneakerList.route
    ) {
        composable(Screen.SneakerList.route) {
            SneakerListView(
                onSneakerDetail = { sneakerId: Long ->
                    navController.navigate(Screen.SneakerDetail.passSneakerId(sneakerId))
                }
            )
        }
        composable(Screen.FavoriteSneakersList.route) {
            FavoriteSneakersListView(
                onSneakerDetail = { sneakerId: Long ->
                    navController.navigate(Screen.SneakerDetail.passSneakerId(sneakerId))
                }
            )
        }
        composable(
            route = Screen.SneakerDetail.route,
            arguments = listOf(
                navArgument(DETAIL_ARGUMENT_SNEAKER_ID) {
                    type = NavType.LongType
                }
            )) { backStackEntry ->
            val sneakerId = backStackEntry.arguments?.getLong(DETAIL_ARGUMENT_SNEAKER_ID) ?: 0L
            SneakerDetailView(sneakerId = sneakerId)
        }
    }
}