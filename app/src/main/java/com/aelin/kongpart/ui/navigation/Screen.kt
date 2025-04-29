package com.aelin.kongpart.ui.navigation

sealed class Screen (val route: String) {
    data object Home: Screen("home")
    data object About: Screen("about")
    data object Part: Screen("home/{category}"){
        fun createRoute(category: String) = "home/$category"
    }
    data object Detail: Screen("home/{category}/{id}"){
        fun createRoute(category: String, id: Int) = "home/$category}/$id"
    }
}