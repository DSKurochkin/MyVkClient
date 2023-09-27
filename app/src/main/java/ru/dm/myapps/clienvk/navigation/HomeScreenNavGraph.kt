package ru.dm.myapps.clienvk.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.gson.Gson
import ru.dm.myapps.clienvk.domain.FeedPost
import ru.dm.myapps.clienvk.utils.genFeedPost


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
                    name = Screen.KEY_POST,
                    builder = { type = NavType.StringType }
                )
            )
        ) {
            val json = it.arguments?.getString(Screen.KEY_POST)
                ?: throw IllegalArgumentException("Post is null")
            val post = Gson().fromJson(json, FeedPost::class.java)
            commentsScreenCallback(genFeedPost())
        }
    }
}