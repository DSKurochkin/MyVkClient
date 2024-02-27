package ru.dm.myapps.clienvk.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.dm.myapps.clienvk.domain.enity.FeedPost

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    newsFeedScreenCallback: @Composable () -> Unit,
    favoriteScreensCallback: @Composable () -> Unit,
    profileScreenCallback: @Composable () -> Unit,
    commentsScreenContentCallback: @Composable (post: FeedPost) -> Unit
) {
    NavHost(navController = navHostController,
        startDestination = Screen.Home.route,
        builder = {
            HomeScreenNavGraph(newsFeedScreenCallback, commentsScreenContentCallback)

            composable(route = Screen.Favorite.route) {
                favoriteScreensCallback()
            }
            composable(route = Screen.PROFILE.route) {
                profileScreenCallback()
            }
        }
    )
}