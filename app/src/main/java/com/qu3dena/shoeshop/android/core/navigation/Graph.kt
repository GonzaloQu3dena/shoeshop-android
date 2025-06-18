package com.qu3dena.shoeshop.android.core.navigation

sealed class Graph(val route: String) {
    object ROOT: Graph("root_graph")
    object CATALOG: Graph("catalog_graph")
}