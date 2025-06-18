package com.qu3dena.shoeshop.android.core.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.qu3dena.shoeshop.android.catalog.presentation.navigation.catalogNavGraph

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    padding: PaddingValues
) {
    NavHost(
        route = Graph.ROOT.route,
        navController = navController,
        startDestination = Graph.CATALOG.route
    ) {
        catalogNavGraph(
            navController = navController
        )
    }
}