package ru.dm.myapps.clienvk.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    newsFeedScreenshotCallback: @Composable () -> Unit,
    favoriteScreenshotCallback: @Composable () -> Unit,
    profileScreenshotCallback: @Composable () -> Unit,
    commentsScreenshotCallback: @Composable () -> Unit
) {
    NavHost(navController = navHostController,
        startDestination = Screen.Home.route,
        builder = {
            HomeScreenNavGraph(newsFeedScreenshotCallback, commentsScreenshotCallback)

            composable(route = Screen.Favorite.route) {
                favoriteScreenshotCallback()
            }
            composable(route = Screen.PROFILE.route) {
                profileScreenshotCallback()
            }
        }
    )
}