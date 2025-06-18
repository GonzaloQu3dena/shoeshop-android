package com.qu3dena.shoeshop.android.catalog.presentation.navigation

import androidx.navigation.navigation
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.qu3dena.shoeshop.android.catalog.presentation.ui.screen.SneakerListView
import com.qu3dena.shoeshop.android.core.navigation.Graph

fun NavGraphBuilder.catalogNavGraph(
    navController: NavHostController
) {
    navigation(
        route = Graph.CATALOG.route,
        startDestination = Screen.SneakerList.route
    ) {
        composable(Screen.SneakerList.route) {
            SneakerListView()
        }
    }
}