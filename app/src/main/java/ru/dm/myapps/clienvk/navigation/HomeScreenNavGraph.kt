package ru.dm.myapps.clienvk.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import ru.dm.myapps.clienvk.domain.FeedPost


fun NavGraphBuilder.HomeScreenNavGraph(
    newsFeedScreenCallback: @Composable () -> Unit,
    commentsScreenCallback: @Composable (post: FeedPost) -> Unit
) {
    navigation(
        startDestination = Screen.NewsFeed.route,
        route = Screen.Home.route
    ) {
        composable(route = Screen.NewsFeed.route) {
            newsFeedScreenCallback()
        }
        composable(
            route = Screen.Comments.route,
            arguments = listOf(
                navArgument(
                    name = Screen.KEY_POST_ID,
                    builder = { type = NavType.IntType }
                )
            )
        ) {
            val postId = it.arguments?.getInt(Screen.KEY_POST_ID) ?: 0
            commentsScreenCallback(FeedPost(id = postId))
        }
    }
}