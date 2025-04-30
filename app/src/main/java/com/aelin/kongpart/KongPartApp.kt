package com.aelin.kongpart

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aelin.kongpart.ui.navigation.NavigationItem
import com.aelin.kongpart.ui.navigation.Screen
import com.aelin.kongpart.ui.screen.about.AboutScreen
import com.aelin.kongpart.ui.screen.detail.DetailScreen
import com.aelin.kongpart.ui.screen.home.HomeScreen
import com.aelin.kongpart.ui.screen.part.PartContent
import com.aelin.kongpart.ui.screen.part.PartScreen
import com.aelin.kongpart.ui.theme.KongPartTheme

@Composable
fun KongPartApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomBarRoute = setOf(
        Screen.Home.route,
        Screen.About.route
    )

    Scaffold(
        bottomBar = {
            if (currentRoute in bottomBarRoute) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToPart = { category ->
                        navController.navigate(Screen.Part.createRoute(category))

                    },
                    navigateToDetail = { category: String, id: Int ->
                        navController.navigate(Screen.Detail.createRoute(category, id))
                    }
                )
            }
            composable(Screen.About.route) {
                AboutScreen()
            }
            composable(
                Screen.Part.route,
                arguments = listOf(navArgument("category") {type = NavType.StringType})
            ) {
                val category = it.arguments?.getString("category") ?: ""

                PartScreen(
                    partCategory = category,
                    navigateToDetail = { partCategory, partId ->
                        navController.navigate(Screen.Detail.createRoute(partCategory, partId))
                    }
                )

            }
            composable(
                Screen.Detail.route,
                arguments = listOf(navArgument("partId") { type = NavType.IntType })
            ) {
                val partId = it.arguments?.getInt("partId") ?: -1
                DetailScreen(
                    partId = partId,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )

            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(modifier = modifier) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home,
            ),
            NavigationItem(
                title = stringResource(R.string.menu_about),
                icon = Icons.Default.AccountCircle,
                screen = Screen.About,
            )
        )

        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun KongPartAppPreview() {
    KongPartTheme {
        KongPartApp()
    }
}