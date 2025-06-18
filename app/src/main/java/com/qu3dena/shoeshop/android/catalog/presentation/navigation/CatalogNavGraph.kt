package com.qu3dena.shoeshop.android.catalog.presentation.navigation

import androidx.navigation.navigation
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
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
            SneakerListView()
        }
    }
}