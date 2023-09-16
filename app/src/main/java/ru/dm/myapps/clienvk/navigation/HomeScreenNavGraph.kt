package ru.dm.myapps.clienvk.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation


fun NavGraphBuilder.HomeScreenNavGraph(
    newsFeedScreenCallback: @Composable () -> Unit,
    commentsScreenCallback: @Composable () -> Unit
) {
    navigation(
        startDestination = Screen.NewsFeed.route,
        route = Screen.Home.route,
        builder = {
            composable(route = Screen.NewsFeed.route) {
                newsFeedScreenCallback()
            }
            composable(route = Screen.Comments.route) {
                commentsScreenCallback()
            }
        })
}